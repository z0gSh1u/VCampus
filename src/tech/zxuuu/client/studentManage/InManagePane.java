package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

/**
 * 入学功能面板
 * 
 * @author 沈汉唐
 * @author z0gSh1u
 */
public class InManagePane extends JPanel {

	private JTextField textCardNumber;
	private JTextField textStudentNumber;
	private JTextField textName;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirm;

	/**
	 * Create the panel.
	 */
	public InManagePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(null);

		JLabel lblInManager = new JLabel("学生入学");
		lblInManager.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblInManager.setBounds(281, 39, 100, 34);
		this.add(lblInManager);

		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(175, 111, 60, 18);
		this.add(lblCardNumber);

		JLabel lbl_StudentNumber = new JLabel("学号");
		lbl_StudentNumber.setBounds(205, 159, 30, 18);
		this.add(lbl_StudentNumber);

		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(205, 205, 30, 18);
		this.add(lblName);

		JLabel lblPassword = new JLabel("密码");
		lblPassword.setBounds(205, 255, 30, 18);
		this.add(lblPassword);

		textCardNumber = new JTextField();
		textCardNumber.setBounds(249, 103, 248, 35);
		this.add(textCardNumber);
		textCardNumber.setColumns(10);

		textStudentNumber = new JTextField();
		textStudentNumber.setBounds(249, 151, 248, 35);
		this.add(textStudentNumber);
		textStudentNumber.setColumns(10);

		textName = new JTextField();
		textName.setBounds(248, 199, 249, 35);
		this.add(textName);
		textName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(249, 247, 248, 35);
		this.add(passwordField);

		JLabel lblPasswordConfirm = new JLabel("确认密码");
		lblPasswordConfirm.setBounds(175, 304, 60, 18);
		this.add(lblPasswordConfirm);

		passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(249, 296, 248, 35);
		this.add(passwordConfirm);

		JButton buttonYes = new JButton("确认");
		buttonYes.setIcon(new ImageIcon(InManagePane.class.getResource("/resources/assets/icon/tick.png")));
		buttonYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textCardNumber.getText().length() != 9 || !SwingUtils.isPureDigits(textCardNumber.getText())
						|| SwingUtils.containsChinese(textCardNumber.getText())) {
					SwingUtils.showError(null, "一卡通错误！", "错误");
				} else if (textStudentNumber.getText().length() != 8
						|| SwingUtils.containsChinese(textStudentNumber.getText())) {
					SwingUtils.showError(null, "学号错误！", "错误");
				} else if (passwordField.getText().length() > 16 || passwordField.getText().length() == 0) {
					SwingUtils.showError(null, "请输入1-16位密码！", "错误");
				} else if (!(passwordField.getText().equals(passwordConfirm.getText()))) {
					SwingUtils.showError(null, "两次输入密码不一致！", "错误");
				} else {
					Student student = new Student(textCardNumber.getText(), null, OtherUtils.getMD5(passwordField.getText()), null);
					student.setName(textName.getText());
					student.setStudentNumber(textStudentNumber.getText());
					student.setAcademy(textStudentNumber.getText().substring(0, 2));
					Response resp = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
							"tech.zxuuu.server.studentManage.StudentManage.insertStudent", new Object[] { student }).send());
					if (resp.getReturn(Boolean.class)) {
						SwingUtils.showMessage(null, "入学成功！", "提示");
					} else {
						SwingUtils.showError(null, "入学失败！", "提示");
					}
				}
			}
		});
		buttonYes.setBounds(347, 354, 150, 57);
		this.add(buttonYes);
	}

}
