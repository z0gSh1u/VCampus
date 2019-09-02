package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewOpencoursePane extends JPanel {
	private JTextField txtCourseName;
	private JTextField txtSpeaker;
	private JTextField txtPreview;
	private JTextField txtVideo;

	/**
	 * Create the panel.
	 */
	public NewOpencoursePane() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("新增公开课");
		lblNewLabel.setBounds(245, 29, 75, 18);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("课程名");
		lblNewLabel_1.setBounds(106, 79, 72, 18);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("讲师");
		lblNewLabel_2.setBounds(106, 129, 72, 18);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("预览图");
		lblNewLabel_3.setBounds(106, 189, 72, 18);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("视频地址");
		lblNewLabel_4.setBounds(106, 240, 72, 18);
		add(lblNewLabel_4);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				OpenCourseInfo openCourseInfo = new OpenCourseInfo();
				openCourseInfo.setCourseName(txtCourseName.getText());
				openCourseInfo.setPreview("<img src=\"" + txtPreview.getText() + "\"/>");
				openCourseInfo.setVideo(txtVideo.getText());
				openCourseInfo.setSpeaker(txtSpeaker.getText());

				Boolean result = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.opencourse.OpencourseManage.insertNewOpencourse", new Object[] { openCourseInfo })
								.send())
						.getReturn(Boolean.class);
				if (result) {
					SwingUtils.showMessage(null, "新增成功", "提示");
				} else {
					SwingUtils.showError(null, "新增失败", "错误");
				}

			}
		});
		btnNewButton.setBounds(253, 295, 113, 27);
		add(btnNewButton);

		txtCourseName = new JTextField();
		txtCourseName.setBounds(201, 76, 86, 24);
		add(txtCourseName);
		txtCourseName.setColumns(10);

		txtSpeaker = new JTextField();
		txtSpeaker.setBounds(201, 126, 86, 24);
		add(txtSpeaker);
		txtSpeaker.setColumns(10);

		txtPreview = new JTextField();
		txtPreview.setBounds(201, 186, 201, 24);
		add(txtPreview);
		txtPreview.setColumns(10);

		txtVideo = new JTextField();
		txtVideo.setBounds(201, 237, 201, 24);
		add(txtVideo);
		txtVideo.setColumns(10);

	}
}
