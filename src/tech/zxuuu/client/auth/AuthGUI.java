package tech.zxuuu.client.auth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tech.zxuuu.client.auth.*;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.UserType;
import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.net.Session;
import tech.zxuuu.client.auth.Listener;
import tech.zxuuu.util.SwingUtils;

public class AuthGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	// 新增属性
	public static ConnectionToServer conn;
	public static ResponseQueue responseQueue;
	private Listener listener;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthGUI frame = new AuthGUI();
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
	public AuthGUI() {
		
		// 尝试连接服务器
		AuthGUI.conn = Utils.formConnection();
		if (AuthGUI.conn != null) {
			AuthGUI.responseQueue = ResponseQueue.getInstance();
			this.listener = new Listener(AuthGUI.conn);
			this.listener.start();
		}
		JLabel lblServerStatus = new JLabel(
			AuthGUI.conn != null ? "连接成功" : "连接失败"
		);
		// 加载服务器相关信息
		JLabel lblServerHost = new JLabel(Utils.getServerHost() + ":" + String.valueOf(Utils.getMainPort()));
		lblServerHost.setBounds(92, 392, 137, 18);
		lblServerStatus.setBounds(229, 392, 72, 18);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pHeader = new JPanel();
		contentPane.add(pHeader, BorderLayout.NORTH);
		
		JLabel lblVcampus = new JLabel("统一登陆认证 - VCampus");
		lblVcampus.setFont(new Font("宋体", Font.PLAIN, 28));
		pHeader.add(lblVcampus);
		
		JPanel pBody = new JPanel();
		contentPane.add(pBody, BorderLayout.CENTER);
		pBody.setLayout(null);
		
		JLabel lblUsername = new JLabel("用户名：");
		lblUsername.setBounds(249, 84, 60, 18);
		pBody.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("宋体", Font.PLAIN, 20));
		txtUsername.setBounds(323, 79, 190, 24);
		pBody.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setBounds(249, 146, 45, 18);
		pBody.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(323, 143, 190, 24);
		pBody.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblType = new JLabel("用户类型：");
		lblType.setBounds(249, 213, 75, 18);
		pBody.add(lblType);
		
		JRadioButton rdoStudent = new JRadioButton("学生");
		rdoStudent.setSelected(true);
		rdoStudent.setBounds(323, 209, 59, 27);
		pBody.add(rdoStudent);
		
		JRadioButton rdoTeacher = new JRadioButton("教师");
		rdoTeacher.setBounds(388, 209, 59, 27);
		pBody.add(rdoTeacher);
		
		JRadioButton rdoManager = new JRadioButton("管理员");
		rdoManager.setBounds(453, 209, 73, 27);
		pBody.add(rdoManager);
		
		JButton btnLogin = new JButton("登陆");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (SwingUtils.isTxtEmpty(txtPassword) || SwingUtils.isTxtEmpty(txtUsername)) {
						SwingUtils.showError(null, "有字段为空！", "错误");
						return;
					}
					UserType type = null;
					if (rdoStudent.isSelected()) {
						type = UserType.STUDENT;
					} else if (rdoTeacher.isSelected()) {
						type = UserType.TEACHER;
					} else if (rdoManager.isSelected()) {
						type = UserType.MANAGER;
					}
					Auth auth = new Auth(txtUsername.getText(), txtPassword.getText(), type);
					System.out.println("Auth result: " + auth.verifyUser());
			}
		});
		btnLogin.setBounds(323, 280, 113, 43);
		pBody.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("服务器状态：");
		lblNewLabel.setBounds(0, 392, 113, 18);
		pBody.add(lblNewLabel);
		pBody.add(lblServerHost);
		pBody.add(lblServerStatus);
		
		JButton button = new JButton("找回密码");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SwingUtils.isTxtEmpty(txtUsername)) {
					SwingUtils.showError(null, "用户名为空", "错误");
					return;
				}
				Request req = new Request(AuthGUI.conn, null, "tech.zxuuu.server.auth.Auth.getPassword", new Object[] {
					txtUsername.getText()
				});
				String hash = req.send();
				while (!ResponseQueue.getInstance().contain(hash)) {
					System.out.println("waiting");
				}
				Response resp = ResponseQueue.getInstance().consume(hash);
				String myPassword = (String) resp.getReturn(String.class);
				System.out.println("My Password = " + myPassword);
			}
		});
		button.setBounds(413, 347, 113, 27);
		pBody.add(button);

	}
}
