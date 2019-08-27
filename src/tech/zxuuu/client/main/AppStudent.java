package tech.zxuuu.client.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class AppStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppStudent frame = new AppStudent();
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
	public AppStudent() {
		setTitle("学生主页 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVcampus = new JLabel("学生主页 - VCampus");
		lblVcampus.setFont(new Font("宋体", Font.PLAIN, 32));
		lblVcampus.setBounds(443, 13, 303, 63);
		contentPane.add(lblVcampus);
		
		JButton btnTeaching = new JButton("教务平台");
		btnTeaching.setBounds(65, 145, 155, 63);
		contentPane.add(btnTeaching);
		
		JButton btnLibrary = new JButton("李文歪图书馆");
		btnLibrary.setBounds(65, 221, 155, 63);
		contentPane.add(btnLibrary);
		
		JButton btnShop = new JButton("天不平超市");
		btnShop.setBounds(65, 297, 155, 63);
		contentPane.add(btnShop);
		
		JButton btnOpencourse = new JButton("在线课堂");
		btnOpencourse.setBounds(65, 376, 155, 63);
		contentPane.add(btnOpencourse);
		
		JLabel lblNameHint = new JLabel("姓名：");
		lblNameHint.setBounds(14, 569, 72, 18);
		contentPane.add(lblNameHint);
		
		JLabel lblCardNumberHint = new JLabel("一卡通号：");
		lblCardNumberHint.setBounds(14, 592, 84, 18);
		contentPane.add(lblCardNumberHint);
		
		JLabel lblStudentNumberHint = new JLabel("学号：");
		lblStudentNumberHint.setBounds(14, 623, 72, 18);
		contentPane.add(lblStudentNumberHint);
		
		JLabel lblAcademyHint = new JLabel("院系：");
		lblAcademyHint.setBounds(14, 654, 72, 18);
		contentPane.add(lblAcademyHint);
		
		JLabel lblBalanceHint = new JLabel("一卡通余额：");
		lblBalanceHint.setBounds(14, 685, 104, 18);
		contentPane.add(lblBalanceHint);
		
		JLabel lblBookLendHint = new JLabel("图书馆借书：");
		lblBookLendHint.setBounds(14, 711, 104, 18);
		contentPane.add(lblBookLendHint);
	}
}
