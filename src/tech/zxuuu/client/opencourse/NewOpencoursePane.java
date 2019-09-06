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
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * 公开课新增面板
 * 
 * @author z0gSh1u
 */
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

		JLabel lblNewLabel = new JLabel("  新增公开课");
		lblNewLabel.setIcon(new ImageIcon(NewOpencoursePane.class.getResource("/resources/assets/icon/add.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel.setBounds(371, 42, 176, 48);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("课程名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(188, 142, 69, 27);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("讲师");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(188, 210, 46, 27);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("预览图URL");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(188, 284, 105, 27);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("视频地址");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 23));
		lblNewLabel_4.setBounds(188, 364, 92, 27);
		add(lblNewLabel_4);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.setIcon(new ImageIcon(NewOpencoursePane.class.getResource("/resources/assets/icon/tick.png")));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
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
		btnNewButton.setBounds(426, 447, 121, 57);
		add(btnNewButton);

		txtCourseName = new JTextField();
		txtCourseName.setBounds(343, 139, 230, 30);
		add(txtCourseName);
		txtCourseName.setColumns(10);

		txtSpeaker = new JTextField();
		txtSpeaker.setBounds(343, 207, 230, 30);
		add(txtSpeaker);
		txtSpeaker.setColumns(10);

		txtPreview = new JTextField();
		txtPreview.setBounds(343, 285, 485, 30);
		add(txtPreview);
		txtPreview.setColumns(10);

		txtVideo = new JTextField();
		txtVideo.setBounds(343, 364, 485, 30);
		add(txtVideo);
		txtVideo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("(120*130)");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 21));
		lblNewLabel_5.setBounds(188, 309, 99, 25);
		add(lblNewLabel_5);

	}
}
