package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.main.AppOpencourseManager;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.BevelBorder;

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
	private AppOpencourseManager mainFrame;

	/**
	 * Create the panel.
	 */
	public NewOpencoursePane(AppOpencourseManager frame) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.mainFrame = frame;
		setLayout(null);

		JLabel lblNew = new JLabel("  新增公开课");
		lblNew.setIcon(new ImageIcon(NewOpencoursePane.class.getResource("/resources/assets/icon/add.png")));
		lblNew.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNew.setBounds(371, 42, 176, 48);

		add(lblNew);

		JLabel lblClassName = new JLabel("课程名");
		lblClassName.setFont(new Font("宋体", Font.PLAIN, 23));
		lblClassName.setBounds(188, 142, 69, 27);
		add(lblClassName);

		JLabel lblSpeaker = new JLabel("讲师");
		lblSpeaker.setFont(new Font("宋体", Font.PLAIN, 23));
		lblSpeaker.setBounds(188, 210, 46, 27);
		add(lblSpeaker);

		JLabel lblPreviewUrl = new JLabel("预览图URL");
		lblPreviewUrl.setFont(new Font("宋体", Font.PLAIN, 23));
		lblPreviewUrl.setBounds(188, 284, 105, 27);
		add(lblPreviewUrl);

		JLabel lblVideoUrl = new JLabel("视频地址");
		lblVideoUrl.setFont(new Font("宋体", Font.PLAIN, 23));
		lblVideoUrl.setBounds(188, 364, 92, 27);

		add(lblVideoUrl);

		JLabel lblShowInfo = new JLabel("");
		lblShowInfo.setForeground(Color.RED);
		lblShowInfo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblShowInfo.setBounds(579, 407, 249, 32);
		add(lblShowInfo);

		JButton btnSubmit = new JButton("新增");
		btnSubmit.setIcon(new ImageIcon(NewOpencoursePane.class.getResource("/resources/assets/icon/tick.png")));
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenCourseInfo openCourseInfo = new OpenCourseInfo();
				openCourseInfo.setCourseName(txtCourseName.getText());
				openCourseInfo.setPreview("<img src=\"" + txtPreview.getText() + "\"/>");
				openCourseInfo.setVideo(txtVideo.getText());
				openCourseInfo.setSpeaker(txtSpeaker.getText());
				if (openCourseInfo.getCourseName().isEmpty() || openCourseInfo.getPreview().isEmpty()
						|| openCourseInfo.getSpeaker().isEmpty() || openCourseInfo.getVideo().isEmpty()) {
					lblShowInfo.setText("有字段为空");
					return;
				}
				Boolean result = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.opencourse.OpencourseManage.insertNewOpencourse", new Object[] { openCourseInfo })
								.send())
						.getReturn(Boolean.class);
				if (result) {
					SwingUtils.showMessage(null, "新增成功", "提示");
					txtCourseName.setText("");
					txtPreview.setText("");
					txtVideo.setText("");
					txtSpeaker.setText("");
					lblShowInfo.setText("");
					mainFrame.showOpenCourseList();
				} else {
					lblShowInfo.setText("新增失败，请确认信息后重试");
				}
			}
		});

		btnSubmit.setBounds(599, 443, 121, 57);
		add(btnSubmit);

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
		JLabel lblPixel = new JLabel("(120*130)");

		lblPixel.setFont(new Font("宋体", Font.PLAIN, 21));
		lblPixel.setBounds(188, 309, 99, 25);
		add(lblPixel);
		
		JButton btnNewButton = new JButton("图片外链平台");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://zxuuu.tech:8080/vcout/form.html"));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "打开失败！", "错误");
				}
			}
		});
		btnNewButton.setBounds(188, 442, 123, 27);
		add(btnNewButton);

	}
}
