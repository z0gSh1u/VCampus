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

import tech.zxuuu.util.SwingUtils;
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

/**
 * 视频播放窗口
 * 
 * @author 第三方库Demo
 */
public class Window extends JFrame {

	private JPanel contentPane; // 顶层容器，整个播放页面的容器
	private JPanel panel; // 控制区域容器
	private JProgressBar progress; // 进度条
	private JPanel progressPanel; // 进度条容器
	private JPanel controlPanel; // 控制按钮容器
	private JButton btnStop, btnPlay, btnPause; // 控制按钮，停止、播放、暂停
	private JSlider slider; // 声音控制块
	EmbeddedMediaPlayerComponent playerComponent; // 媒体播放器组件
	private JLabel lblNewLabel;
	public String videoUrl;

	public Window(JFrame wolf) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				StuCourseGUI.exit();
				wolf.dispose();
			}
		});
		setTitle("在线课程播放器 - VCampus");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		/* 视屏窗口中播放界面部分 */
		JPanel videoPane = new JPanel();
		contentPane.add(videoPane, BorderLayout.CENTER);
		videoPane.setLayout(new BorderLayout(0, 0));
		playerComponent = new EmbeddedMediaPlayerComponent();
		videoPane.add(playerComponent);
		/* 视屏窗口中控制部分 */
		panel = new JPanel(); // 实例化控制区域容器
		videoPane.add(panel, BorderLayout.SOUTH);
		
		Window that = this;
		lblNewLabel = new JLabel("点击下载视频...     ");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(that.videoUrl));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "默认下载器启动失败！", "错误");
				}
			}
		});
		lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);
		progressPanel = new JPanel(); // 实例化进度条容器
		panel.add(progressPanel, BorderLayout.NORTH);
		// 添加进度条
		progress = new JProgressBar();
		progressPanel.add(progress);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 点击进度条调整视屏播放进度
				int x = e.getX();
				StuCourseGUI.jumpTo((float) x / progress.getWidth());
			}
		});
		progress.setStringPainted(true);
		controlPanel = new JPanel(); // 实例化控制按钮容器
		panel.add(controlPanel, BorderLayout.SOUTH);
		// 添加停止按钮
		btnStop = new JButton("停止");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StuCourseGUI.stop();
			}
		});
		controlPanel.add(btnStop);
		// 添加播放按钮
		btnPlay = new JButton("播放");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StuCourseGUI.play();
			}
		});
		controlPanel.add(btnPlay);
		// 添加暂停按钮
		btnPause = new JButton("暂停");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StuCourseGUI.pause();
			}
		});
		controlPanel.add(btnPause);
		// 添加声音控制块
		slider = new JSlider();
		slider.setValue(80);
		slider.setMaximum(100);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				StuCourseGUI.setVol(slider.getValue());
			}
		});
		controlPanel.add(slider);
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
