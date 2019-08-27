package tech.zxuuu.client.studentManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InManager extends JFrame {

	private JPanel contentPane;
	private JTextField textCardNumber;
	private JTextField textStudentNumber;
	private JTextField textName;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InManager frame = new InManager();
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
	public InManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInManager = new JLabel("学生入学管理");
		lblInManager.setBounds(265, 0, 333, 82);
		contentPane.add(lblInManager);
		
		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(147, 111, 115, 50);
		contentPane.add(lblCardNumber);
		
		JLabel lbl_StudentNumber = new JLabel("学号");
		lbl_StudentNumber.setBounds(171, 182, 63, 44);
		contentPane.add(lbl_StudentNumber);
		
		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(171, 236, 69, 60);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("密码");
		lblPassword.setBounds(171, 307, 48, 44);
		contentPane.add(lblPassword);
		
		textCardNumber = new JTextField();
		textCardNumber.setBounds(275, 119, 248, 35);
		contentPane.add(textCardNumber);
		textCardNumber.setColumns(10);
		
		textStudentNumber = new JTextField();
		textStudentNumber.setBounds(275, 187, 248, 35);
		contentPane.add(textStudentNumber);
		textStudentNumber.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(274, 249, 249, 35);
		contentPane.add(textName);
		textName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(275, 312, 250, 35);
		contentPane.add(passwordField);
		
		JLabel lblPasswordConfirm = new JLabel("确认密码");
		lblPasswordConfirm.setBounds(147, 372, 147, 44);
		contentPane.add(lblPasswordConfirm);
		
		passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(275, 377, 248, 35);
		contentPane.add(passwordConfirm);
		
		JButton buttonYes = new JButton("确认");
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textCardNumber.getText().length()!=9) {SwingUtils.showMessage(null, "一卡通长度错误！！", "错误");}
				else if(textStudentNumber.getText().length()!=8) {SwingUtils.showMessage(null, "学号长度错误！", "错误");}
				else if(passwordField.getText().length()>16) {SwingUtils.showMessage(null, "请输入16位以内密码！", "错误");}
				else if(!(passwordField.getText().equals(passwordConfirm.getText()))) {SwingUtils.showMessage(null, "两次输入密码不一致！", "错误");}
				else 
				{
				Student student = new Student(textCardNumber.getText(), passwordField.getText());
				student.setName(textName.getText());
				student.setStudentNumber(textStudentNumber.getText());
				student.setAcademy(textStudentNumber.getText().substring(0, 2));
				
				System.out.println(student.toString());
				
				Request request = new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.insertStudent", 
					new Object[] {student});
				String hash = request.send();
				
				ResponseUtils.blockAndWaitResponse(hash);
				Response resp = ResponseQueue.getInstance().consume(hash);
				
				if (resp.getReturn(Boolean.class)) {
					SwingUtils.showMessage(null, "入学成功！", "hint");
				} else {
					SwingUtils.showMessage(null, "入学失败！", "hint");
				}
				}
			}
		});
		buttonYes.setBounds(537, 448, 153, 37);
		contentPane.add(buttonYes);
	}
}
