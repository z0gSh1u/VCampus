package tech.zxuuu.client.studentManage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import tech.zxuuu.net.Request;
import tech.zxuuu.client.main.App;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class ResetPasswordPane extends JPanel {
	private JTextField txtNameDisp;
	private JTextField txtPassword;
	private JTextField txtPasswordAgain;
	private JTextField txtCardNumber;

	/**
	 * Create the panel.
	 */
	public ResetPasswordPane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("学生密码修改");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblNewLabel.setIcon(new ImageIcon(ResetPasswordPane.class.getResource("/resources/assets/icon/keyicon.png")));
		lblNewLabel.setBounds(206, 29, 202, 48);
		add(lblNewLabel);

		JLabel lblCardNumber = new JLabel("一卡通号：");
		lblCardNumber.setBounds(105, 117, 75, 18);
		add(lblCardNumber);

		JLabel lblName = new JLabel("姓名：");
		lblName.setBounds(105, 174, 72, 18);
		add(lblName);

		txtNameDisp = new JTextField();
		txtNameDisp.setEditable(false);
		txtNameDisp.setBounds(206, 171, 202, 24);
		add(txtNameDisp);
		txtNameDisp.setColumns(10);

		JButton btnCheck = new JButton("检测");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SwingUtils.isTxtEmpty(txtCardNumber) || !SwingUtils.isPureDigits(txtCardNumber.getText())) {
					SwingUtils.showError(null, "一卡通输入非法！", "错误");
					return;
				}
				String name = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.getNameByCardNumber",
						new Object[] { txtCardNumber.getText() }).send()).getReturn(String.class);
				if (name == null || name.equals("")) {
					SwingUtils.showError(null, "找不到该学生！", "错误");
					return;
				}
				txtNameDisp.setText(name);
			}
		});
		btnCheck.setBounds(433, 113, 63, 27);
		add(btnCheck);

		JLabel lblNewPassword = new JLabel("新密码：");
		lblNewPassword.setBounds(105, 228, 72, 18);
		add(lblNewPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(206, 225, 202, 24);
		add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblNewPasswordAgain = new JLabel("确认密码：");
		lblNewPasswordAgain.setBounds(105, 279, 75, 18);
		add(lblNewPasswordAgain);

		txtPasswordAgain = new JTextField();
		txtPasswordAgain.setBounds(206, 276, 202, 24);
		add(txtPasswordAgain);
		txtPasswordAgain.setColumns(10);

		JButton btnSubmit = new JButton("提交");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SwingUtils.isTxtEmpty(txtPassword) || (!txtPassword.getText().equals(txtPasswordAgain.getText()))) {
					SwingUtils.showError(null, "请检查密码输入！", "错误");
					return;
				}
				Boolean ret = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.resetPassword",
										new Object[] { txtCardNumber.getText(), OtherUtils.getMD5(txtPassword.getText()) }).send())
						.getReturn(Boolean.class);
				if (ret) {
					SwingUtils.showMessage(null, "改密成功！", "提示");
					return;
				} else {
					SwingUtils.showError(null, "改密失败！", "提示");
					return;
				}
			}
		});
		btnSubmit.setIcon(new ImageIcon(ResetPasswordPane.class.getResource("/resources/assets/icon/tick.png")));
		btnSubmit.setBounds(244, 331, 115, 57);
		add(btnSubmit);

		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(206, 114, 202, 24);
		add(txtCardNumber);
		txtCardNumber.setColumns(10);

	}
}
