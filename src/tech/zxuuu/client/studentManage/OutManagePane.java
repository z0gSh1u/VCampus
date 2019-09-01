package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * 退学功能面板
 * 
 * @version 已review完成，已美化
 */
public class OutManagePane extends JPanel {

	private JTextField textCardNumber;

	public OutManagePane() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton buttonYes = new JButton("确定");
		buttonYes.setIcon(new ImageIcon(OutManagePane.class.getResource("/resources/assets/icon/tick.png")));
		buttonYes.setBounds(385, 187, 115, 57);
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textCardNumber.getText().length() != 9) {
					SwingUtils.showError(null, "一卡通长度错误！", "错误");
					return;
				}
				String cardnumber = textCardNumber.getText();
				String studentName = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.getNameByCardNumber", new Object[] { cardnumber }).send())
						.getReturn(String.class);
				if (studentName == null) {
					SwingUtils.showError(null, "查无此人，退学失败！", "提示");
					return;
				}
				String reInput = SwingUtils.popInput("要退学的学生姓名为 " + studentName + " ，请再次输入一卡通号确认：");
				if (!reInput.equals(cardnumber)) {
					SwingUtils.showMessage(null, "退学已取消！", "提示");
					return;
				}
				Response resp = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.deleteStudent", new Object[] { cardnumber }).send());
				if (resp.getReturn(Boolean.class)) {
					SwingUtils.showMessage(null, "退学成功！", "提示");
				} else {
					SwingUtils.showError(null, "发生错误，退学失败！", "提示");
				}
			}
		});

		setLayout(null);
		this.add(buttonYes);

		JLabel lblOutManage = new JLabel("学生退学");
		lblOutManage.setBounds(254, 51, 100, 34);
		lblOutManage.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		this.add(lblOutManage);

		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(159, 129, 60, 18);
		this.add(lblCardNumber);

		textCardNumber = new JTextField();
		textCardNumber.setBounds(233, 120, 267, 37);
		this.add(textCardNumber);
		textCardNumber.setColumns(10);
	}

}
