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
import tech.zxuuu.server.opencourse.ChatSocket;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class StuCourseGUI extends JFrame {

	private JPanel contentPane;
	private JEditorPane epnInputBox;
	private JEditorPane epnChatBox;

	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;
	private int courseId;
	private ChatSocket chatSocket;

	static Window videoFrame;

	private void updateMessage(String str, ChatSocket listener) {
		System.out.println("[StuCourseGUI]收到新信息:" + str);
		String text = epnChatBox.getText();
		int s = text.indexOf("<body>");
		int f = text.lastIndexOf("</body>");
		text = text.substring(s + 11, f - 3);
		epnChatBox.setText(text + str);
		epnChatBox.grabFocus();
		epnInputBox.grabFocus();
	}

	private Boolean sendMessage(String str) {

		if (str.isEmpty())
			return false;
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("\r", "");

		this.chatSocket.write(str);
		return true;
	}

	private Boolean connect() {
		try {
			new Thread((this.chatSocket = new ChatSocket(new Socket("1277.0.0.1", 1984),
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
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private void init() {
		connect();
	}

	/**
	 * Create the frame.
	 */
	public StuCourseGUI(int courseId, String videoUrl) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(StuCourseGUI.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("实时聊天 - VCampus");

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC\\sdk\\lib"); // 导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					videoFrame = new Window();
					videoFrame.setVisible(true);
					videoFrame.getMediaPlayer().playMedia(videoUrl);

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
					e.printStackTrace();
				}
			}
		});

		this.courseId = courseId;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		frameWidth = 800;
		frameHeight = 600;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, 406, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		epnInputBox = new JEditorPane();
		epnInputBox.setBounds(137, 416, 218, 75);
		contentPane.add(epnInputBox);

		epnChatBox = new JEditorPane();
		epnChatBox.setEditable(false);
		epnChatBox.setContentType("text/html");
		epnChatBox.setBounds(33, 13, 322, 390);
		contentPane.add(epnChatBox);

		JButton btnSendMess = new JButton("发送");
		btnSendMess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(epnInputBox.getText());
				epnInputBox.setText("");
			}
		});
		epnInputBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(epnInputBox.getText());
					epnInputBox.setText("");
				}
			}
		});
		btnSendMess.setBounds(33, 504, 322, 37);
		contentPane.add(btnSendMess);

		JPanel pnlEmoticon = new JPanel();
		pnlEmoticon.setBounds(33, 416, 90, 75);
		contentPane.add(pnlEmoticon);
		pnlEmoticon.setLayout(new GridLayout(0, 10, 0, 0));

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
		System.exit(0);
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
