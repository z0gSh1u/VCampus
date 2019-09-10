package tech.zxuuu.client.opencourse;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.main.Utils;
import tech.zxuuu.entity.EmoticonInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.server.opencourse.ChatSocket;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_state_t;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * 学生课程播放界面
 * 
 * @author LongChen
 * @modify z0gSh1u
 */
public class StuCourseGUI extends JFrame {

	private JPanel contentPane;
	private JEditorPane epnInputBox;
	private JEditorPane epnChatBox;
	private JScrollPane scpChatBox;

	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;
	private int courseId;
	private String courseName;
	private ChatSocket chatSocket;
	private Map<String, String> emoticonMap;

	static Window videoFrame;
	private JScrollPane scpEmoticonList;
	private JPanel pnlEmoticonList;

	private void updateMessage(String str, ChatSocket listener) {
		String text = epnChatBox.getText();
		int s = text.indexOf("<body>");
		int f = text.lastIndexOf("</body>");
		text = text.substring(s + 11, f - 3);
		epnChatBox.setText(text + str);
		try {
			epnChatBox.validate();
			epnChatBox.repaint();
			epnChatBox.updateUI();
			epnChatBox.revalidate();
		} catch (Exception e) {
			// Don't care.
		}

		scpChatBox.getVerticalScrollBar().setValue(scpChatBox.getVerticalScrollBar().getMaximum());

	}

	private String toEmoticon(String str) {
		int curPos = str.indexOf("\\");
		while (curPos > -1) {
			int endPos = str.indexOf("/", curPos);
			if (endPos == -1)
				break;
			String name = str.substring(curPos + 1, endPos);
			String emo = emoticonMap.get(name);
			if (emo != null) {
				str = str.substring(0, curPos) + emo + str.substring(endPos + 1);
			}
			curPos = str.indexOf("\\", curPos + 1);
		}
		return str;
	}

	private Boolean sendMessage(String str) {
		if (str.isEmpty())
			return false;
		scpEmoticonList.setVisible(false);
		str = toEmoticon(str);
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("\r", "");

		this.chatSocket.write(str);
		return true;
	}

	private Boolean connect() {
		try {
			new Thread((this.chatSocket = new ChatSocket(new Socket(Utils.getServerHost(), Utils.getChatPort()),
					(String str, ChatSocket chatSocket) -> updateMessage(str, chatSocket)))).start();
			this.chatSocket.setUserType(App.session.getUserType());
			switch (this.chatSocket.getUserType()) {
			case STUDENT:
				this.chatSocket.setName(App.session.getStudent().getName());
				break;
			case TEACHER:
				this.chatSocket.setName(App.session.getTeacher().getName());
				break;
			case MANAGER:
				this.chatSocket.setName(App.session.getManager().getCardNumber());
				break;
			}
			this.chatSocket.setCourseId(courseId);
			this.chatSocket.write(Integer.toString(this.courseId) + "\\" + JSON.toJSONString(this.chatSocket.getUserType())
					+ "\\" + this.chatSocket.getName());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private Boolean listEmoticon() {
		this.emoticonMap = new HashMap<String, String>();
		List<EmoticonInfo> emoticonList = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.StuCourse.getEmoticonList", null)
						.send())
				.getListReturn(EmoticonInfo.class);
		if (emoticonList.isEmpty()) {
			return false;
		}
		for (EmoticonInfo emoInfo : emoticonList) {
			emoticonMap.put(emoInfo.getName(), emoInfo.getAddr());
			JEditorPane newEmo = new JEditorPane();
			newEmo.setContentType("text/html");
			newEmo.setText(emoInfo.getAddr());
			newEmo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					epnInputBox.setText(epnInputBox.getText() + "\\" + emoInfo.getName() + "/");

					scpEmoticonList.setVisible(false);
					epnInputBox.grabFocus();

				}
			});
			newEmo.setEditable(false);
			pnlEmoticonList.add(newEmo);
		}
		return true;
	}

	private void init() {
		connect();
		listEmoticon();
	}

	/**
	 * Create the frame.
	 */
	public StuCourseGUI(int courseId, String videoUrl, String courseName) {
		
		this.courseName = courseName;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				videoFrame.getMediaPlayer().release();
				videoFrame.dispose();
			}
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(StuCourseGUI.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("实时聊天 - VCampus  正在播放："+courseName);
		Properties prop = new Properties();
		try {
			prop.load(StuCourseGUI.class.getResourceAsStream("/resources/vlc.properties"));
		} catch (IOException e1) {
			SwingUtils.showError(null, "VLC配置读取失败！", "错误");
		}
		
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), prop.getProperty("vlcSDKPath")); // 导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		JFrame that = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					videoFrame = new Window(that, courseName);
					videoFrame.setBounds((screenWidth - videoFrame.getWidth() - frameWidth) / 2,
							(screenHeight - videoFrame.getHeight()) / 2, videoFrame.getWidth(), videoFrame.getHeight());
					that.setBounds((screenWidth - videoFrame.getWidth() - frameWidth) / 2 + videoFrame.getWidth(),
							(screenHeight - videoFrame.getHeight()) / 2, frameWidth, frameHeight);
					videoFrame.setVisible(true);
					videoFrame.getMediaPlayer().playMedia(videoUrl);
					videoFrame.videoUrl = videoUrl;
					new SwingWorker<String, Integer>() {
						@Override
						protected String doInBackground() throws Exception {
							while (true) { // 获取视频播放进度并且按百分比显示
								long total = videoFrame.getMediaPlayer().getLength();
								long curr = videoFrame.getMediaPlayer().getTime();
								float percent = (float) curr / total;
								publish((int) (percent * 100));
								Thread.sleep(100);
							}
						}

						@Override
						protected void process(java.util.List<Integer> chunks) {
							for (int v : chunks) {
								videoFrame.getProgressBar().setValue(v);
							}
						}
					}.execute();
				} catch (Exception e) {
					// dont care
				}
			}
		});

		this.courseId = courseId;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth = (int) screenSize.getWidth();
		this.screenHeight = (int) screenSize.getHeight();
		this.frameWidth = 406;
		this.frameHeight = 600;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scpEmoticonList = new JScrollPane();
		scpEmoticonList.setBounds(33, 84, 322, 293);
		contentPane.add(scpEmoticonList);
		scpEmoticonList.setVisible(false);
		scpEmoticonList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnlEmoticonList = new JPanel();
		scpEmoticonList.setViewportView(pnlEmoticonList);
		pnlEmoticonList.setBackground(Color.LIGHT_GRAY);
		pnlEmoticonList.setLayout(new GridLayout(0, 4, 0, 0));

		JButton btnSendMess = new JButton("发送 (Ctrl + Enter)");
		btnSendMess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(epnInputBox.getText());
				epnInputBox.setText("");
			}
		});
		btnSendMess.setBounds(33, 489, 322, 37);
		contentPane.add(btnSendMess);
		scpChatBox = new JScrollPane();
		scpChatBox.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scpChatBox.setBounds(33, 13, 322, 365);
		contentPane.add(scpChatBox);

		epnChatBox = new JEditorPane();
		scpChatBox.setViewportView(epnChatBox);
		epnChatBox.setEditable(false);
		epnChatBox.setContentType("text/html");

		JEditorPane epnShowEmoticon = new JEditorPane();
		epnShowEmoticon.setEditable(false);
		epnShowEmoticon.setContentType("text/html");
		epnShowEmoticon.setBounds(33, 379, 28, 28);
		epnShowEmoticon.setText("<img src=\"https://s2.ax1x.com/2019/09/04/nEpEF0.png\"/>");
		epnShowEmoticon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scpEmoticonList.setVisible(!scpEmoticonList.isVisible());
			}
		});

		contentPane.add(epnShowEmoticon);
		
		JScrollPane scpInputBox = new JScrollPane();
		scpInputBox.setBounds(33, 408, 322, 68);
		contentPane.add(scpInputBox);
		
				epnInputBox = new JEditorPane();
				scpInputBox.setViewportView(epnInputBox);
				epnInputBox.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
							sendMessage(epnInputBox.getText());
							epnInputBox.setText("");
						}
					}
				});


		epnChatBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scpEmoticonList.setVisible(false);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scpEmoticonList.setVisible(false);
			}
		});

		init();
	}

	// 打开文件
	public static void openVideo() {
		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			videoFrame.getMediaPlayer().playMedia(file.getAbsolutePath());
		}
	}

	// 退出播放
	public static void exit() {
		videoFrame.getMediaPlayer().release();
		// System.exit(0);
	}

	// 实现播放按钮的方法
	public static void play() {
		videoFrame.getMediaPlayer().play();
	}

	// 实现暂停按钮的方法
	public static void pause() {
		videoFrame.getMediaPlayer().pause();
	}

	// 实现停止按钮的方法
	public static void stop() {
		videoFrame.getMediaPlayer().stop();
	}

	// 实现点击进度条跳转的方法
	public static void jumpTo(float to) {
		videoFrame.getMediaPlayer().setTime((long) (to * videoFrame.getMediaPlayer().getLength()));
	}

	// 实现控制声音的方法
	public static void setVol(int v) {
		videoFrame.getMediaPlayer().setVolume(v);
	}
}
