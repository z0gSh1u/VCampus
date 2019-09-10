package tech.zxuuu.client.opencourse;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.java.swing.plaf.windows.resources.windows;

import tech.zxuuu.util.SwingUtils;
import uk.co.caprica.vlcj.binding.internal.libvlc_state_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * 视频播放窗口
 * 
 * @author 第三方库Demo
 */
public class Window extends JFrame {

	private JPanel contentPane; // 顶层容器，整个播放页面的容器
	private JPanel pnlOpearate; // 控制区域容器
	private JProgressBar progress; // 进度条
	private JButton btnStop, btnPlay; // 控制按钮，停止、播放、暂停
	private JSlider slider; // 声音控制块
	EmbeddedMediaPlayerComponent playerComponent; // 媒体播放器组件
	public String videoUrl;
	private JButton btnDownload;
	private JButton btnVol;
	
	private Boolean mouseOnBtnVol;
	private Boolean mouseOnSlider;
	private Boolean isSilence;

	public Window(JFrame wolf, String courseName) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/resources/assets/icon/fav.png")));
		mouseOnBtnVol = mouseOnSlider = isSilence = false;
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				StuCourseGUI.exit();
				wolf.dispose();
			}
		});
		setTitle("在线课程播放器 - VCampus  正在播放：" + courseName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		/* 视屏窗口中播放界面部分 */
		contentPane.setLayout(null);
		JPanel videoPane = new JPanel();
		videoPane.setBounds(5, 5, 884, 561);
		contentPane.add(videoPane);
		videoPane.setLayout(null);
		
		JPanel pnlVol = new JPanel();
		pnlVol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOnSlider = true;
				pnlVol.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOnSlider = false;
				if(!mouseOnBtnVol && !mouseOnSlider) {
					pnlVol.setVisible(false);
				}
			}
		});
		pnlVol.setOpaque(false);
		pnlVol.setBounds(702, 317, 30, 195);
		videoPane.add(pnlVol);
		// 添加声音控制块
		slider = new JSlider();
		slider.setOpaque(false);
		pnlVol.add(slider);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setValue(80);
		slider.setMaximum(100);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				StuCourseGUI.setVol(slider.getValue());
				isSilence = false;
				btnVol.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_voice.png")));
			}
		});
		pnlVol.setVisible(false);
		playerComponent = new EmbeddedMediaPlayerComponent();
		playerComponent.setBounds(0, 0, 884, 492);
		videoPane.add(playerComponent);
		/* 视屏窗口中控制部分 */
		pnlOpearate = new JPanel(); // 实例化控制区域容器
		pnlOpearate.setBounds(0, 492, 884, 69);
		videoPane.add(pnlOpearate);
		
		Window that = this;
		pnlOpearate.setLayout(null);
		// 添加暂停按钮
		btnPlay = new JButton("");
		btnPlay.setBounds(53, 16, 57, 42);
		pnlOpearate.add(btnPlay);
		btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_pause.png")));
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				libvlc_state_t state = StuCourseGUI.videoFrame.getMediaPlayer().getMediaPlayerState();
				if(state.equals(libvlc_state_t.libvlc_Playing)) {
					StuCourseGUI.pause();
					btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_play.png")));
				}
				else if(state.equals(libvlc_state_t.libvlc_Paused) || state.equals(libvlc_state_t.libvlc_Stopped)) {
					StuCourseGUI.play();
					btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_pause.png")));
				}else if(progress.getValue() == 100) {
					StuCourseGUI.stop();
					StuCourseGUI.play();
					btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_pause.png")));
				}
				
			}
		});
		// 添加停止按钮
		btnStop = new JButton("");
		btnStop.setBounds(120, 16, 57, 42);
		pnlOpearate.add(btnStop);
		btnStop.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_stop.png")));
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StuCourseGUI.stop();
				btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_play.png")));
			}
		});
		
		btnDownload = new JButton("下载");
		btnDownload.setBounds(765, 5, 109, 53);
		pnlOpearate.add(btnDownload);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(that.videoUrl));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "默认下载器启动失败！", "错误");
				}
			}
		});
		btnDownload.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/借出.png")));
		// 添加进度条
		progress = new JProgressBar();
		progress.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(progress.getValue() == 100) {
					btnPlay.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_play.png")));
				}
			}
		});
		progress.setForeground(Color.RED);
		progress.setBounds(187, 31, 490, 7);
		pnlOpearate.add(progress);
		
		btnVol = new JButton("");
		btnVol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOnBtnVol = true;
				mouseOnSlider = true;
				pnlVol.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOnBtnVol = false;
				if(!mouseOnBtnVol && !mouseOnSlider) {
					pnlVol.setVisible(false);
				}
			}
		});
		btnVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mouseOnSlider = false;
				if(isSilence) {
					StuCourseGUI.setVol(slider.getValue());
					btnVol.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_voice.png")));
					isSilence = false;
					
				}else {
					StuCourseGUI.setVol(0);
					btnVol.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_silence.png")));
					isSilence = true;
				}
			}
		});
		btnVol.setIcon(new ImageIcon(Window.class.getResource("/resources/assets/icon/video_voice.png")));
		btnVol.setBounds(687, 16, 57, 42);
		pnlOpearate.add(btnVol);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 点击进度条调整视屏播放进度
				int x = e.getX();
				StuCourseGUI.jumpTo((float) x / progress.getWidth());
			}
		});
	}

	// 获取播放媒体实例
	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
	}

	// 获取进度条实例
	public JProgressBar getProgressBar() {
		return progress;
	}
}
