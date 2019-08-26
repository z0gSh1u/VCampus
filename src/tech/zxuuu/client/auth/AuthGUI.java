package tech.zxuuu.client.auth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.entity.UserType;
import tech.zxuuu.net.Session;
import tech.zxuuu.util.SwingUtils;
import javax.swing.JPasswordField;

/**
 * 登陆界面GUI
 * @author z0gSh1u
 */
public class AuthGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) {
				// 输入合法检查
				if (SwingUtils.isTxtEmpty(txtPassword) || SwingUtils.isTxtEmpty(txtUsername)) {
					SwingUtils.showError(null, "有字段为空！", "错误");
					return;
				}
				UserType type = null;
				if (rdoStudent.isSelected()) {
					type = UserType.STUDENT;
					Boolean res = AuthHelper.verifyStudent(txtUsername.getText(), txtPassword.getText());
					if (res) {
						SwingUtils.showMessage(null, "学生登陆成功！", "信息");
						App.hasLogon = true;
						App.session = new Session(new Student(txtUsername.getText(), txtPassword.getText()));
						// TODO: 是否考虑verifyStudent返回完整的学生对象
//						Container ctn = getParent();
//						while (!(ctn instanceof JFrame)) {
//							ctn = ctn.getParent();
//						}
//						JFrame jf = (JFrame) ctn;
//						jf.setVisible(false);
					} else {
						SwingUtils.showError(null, "密码错误，登陆失败！", "错误");
					}
				} else if (rdoTeacher.isSelected()) {
					type = UserType.TEACHER;
					// TODO: 添加处理逻辑
				} else if (rdoManager.isSelected()) {
					type = UserType.MANAGER;
					// TODO: 添加处理逻辑
				}
			}
		});
		
		btnLogin.setBounds(323, 280, 113, 43);
		pBody.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(323, 143, 190, 24);
		pBody.add(txtPassword);

	}
}
