package tech.zxuuu.server.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import tech.zxuuu.net.RequestListener;
import tech.zxuuu.net.ResponseListener;
import tech.zxuuu.server.messageQueue.RequestHandler;
import tech.zxuuu.server.messageQueue.RequestQueue;
import tech.zxuuu.util.ServerUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class App extends JFrame {

	private JPanel contentPane;
	
	// 添加属性
	private RequestListener requestListener;
	// 服务器端全局请求消息队列
	public static RequestQueue requestQueue;	
	public static RequestHandler requestHandler;
	public static SqlSessionFactory sqlSessionFactory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		
		/**
		 * 新增部分
		 */
		
		// 初始化全局消息队列
		App.requestQueue = RequestQueue.getInstance();
		// 初始化MyBatis的SqlSession工厂
		String resource = "resources/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 启动服务器端侦听
		requestListener = new RequestListener(Integer.parseInt(ServerUtils.getMainPort()));
		requestListener.start();
		// 启动请求处理器
		App.requestHandler = new RequestHandler();
		App.requestHandler.start();
		
		/*****************************************/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		SwingUtils.showMessage(null, "开始服务器端侦听", "信息");
		
	}

}
