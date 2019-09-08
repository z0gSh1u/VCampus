package tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.NoticeInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class NewNoticePane extends JPanel {
	private JTextField txtTitle;
	private JTextField txtUrl;

	/**
	 * Create the panel.
	 */
	public NewNoticePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("发布新通知");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblNewLabel.setIcon(new ImageIcon(NewNoticePane.class.getResource("/resources/assets/icon/jianjie.png")));
		lblNewLabel.setBounds(227, 36, 161, 34);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("通知标题");
		lblNewLabel_1.setBounds(122, 115, 72, 18);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("跳转网址");
		lblNewLabel_2.setBounds(122, 180, 72, 18);
		add(lblNewLabel_2);

		JButton btnNewButton = new JButton("发布");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (SwingUtils.isTxtEmpty(txtTitle) || SwingUtils.isTxtEmpty(txtUrl)) {
					SwingUtils.showError(null, "有字段为空！", "错误");
					return;
				}

				Long unixTimeStamp = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
								.send())
						.getReturn(Long.class);

				Date date = new Date(unixTimeStamp);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String dateString = simpleDateFormat.format(date);

				NoticeInfo noticeInfo = new NoticeInfo(txtTitle.getText(), dateString, txtUrl.getText());
				Boolean result = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.teaching.CourseManagerSide.addNotice", new Object[] { noticeInfo }).send())
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "添加成功！", "提示");
				} else {
					SwingUtils.showError(null, "添加失败！", "错误");
				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(NewNoticePane.class.getResource("/resources/assets/icon/tick.png")));
		btnNewButton.setBounds(248, 258, 115, 57);
		add(btnNewButton);

		txtTitle = new JTextField();
		txtTitle.setBounds(208, 112, 274, 24);
		add(txtTitle);
		txtTitle.setColumns(10);

		txtUrl = new JTextField();
		txtUrl.setBounds(208, 177, 274, 24);
		add(txtUrl);
		txtUrl.setColumns(10);

	}
}
