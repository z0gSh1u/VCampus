package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.opencourse.ListOpencoursePane;
import tech.zxuuu.client.opencourse.NewOpencoursePane;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AppOpencourseManager extends JFrame {

	private JPanel contentPane;
	private JPanel currentDisplay;
	private JPanel listOpencoursePane;
	private JPanel newOpencoursePane;
	private JPanel defaultPane;

	/**
	 * Create the frame.
	 */
	public AppOpencourseManager() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AppOpencourseManager.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("公开课管理员 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 971, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppOpencourseManager.class.getResource("/resources/assets/icon/opencourse.png")));
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("公开课管理 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 264, 34);
		contentPane.add(lblVcampus);

		JLabel label_2 = new JLabel("当前登录卡号：");
		label_2.setBounds(380, 48, 105, 18);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel((String) null);
		label_3.setBounds(488, 48, 114, 18);
		contentPane.add(label_3);
		label_3.setText(App.session.getManager().getCardNumber());

		JButton btnNewButton = new JButton("公开课列表");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				listOpencoursePane.setVisible(true);
				currentDisplay = listOpencoursePane;
			}
		});
		btnNewButton.setBounds(647, 22, 113, 53);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("新增公开课");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newOpencoursePane.setVisible(true);
				currentDisplay = newOpencoursePane;
			}
		});
		btnNewButton_1.setBounds(774, 50, 113, 27);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("删除公开课");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idToDelete = SwingUtils.popInput("请输入要删除的公开课的Id：");

				Boolean result = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.OpencourseManage.deleteOpencourse",
								new Object[] { Integer.parseInt(idToDelete) }).send())
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "删除成功", "提示");
				} else {
					SwingUtils.showError(null, "删除失败", "提示");
				}

			}
		});
		btnNewButton_2.setBounds(774, 22, 113, 27);
		contentPane.add(btnNewButton_2);

		defaultPane = new JPanel();
		defaultPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		defaultPane.setBounds(14, 90, 925, 539);
		contentPane.add(defaultPane);

		JLabel label_1 = new JLabel("选择一项功能以开始...");
		defaultPane.add(label_1);

		listOpencoursePane = new ListOpencoursePane();
		listOpencoursePane.setBounds(14, 90, 925, 539);
		listOpencoursePane.setVisible(false);
		contentPane.add(listOpencoursePane);

		newOpencoursePane = new NewOpencoursePane();
		newOpencoursePane.setBounds(14, 90, 925, 539);
		newOpencoursePane.setVisible(false);
		contentPane.add(newOpencoursePane);

		currentDisplay = defaultPane;

	}
}
