package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.main.AppOpencourseManager;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * 公开课新增面板
 * 
 * @author z0gSh1u
 */
public class DeleteOpencoursePane extends JPanel {
	private JTextField txtCourseName;
	private AppOpencourseManager mainFrame;

	/**
	 * Create the panel.
	 */
	public DeleteOpencoursePane(AppOpencourseManager frame) {
		this.mainFrame = frame;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("删除公开课");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(245, 29, 90, 24);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("课程ID");
		lblNewLabel_1.setBounds(106, 79, 72, 18);
		add(lblNewLabel_1);
		
		JLabel lblShowInfo = new JLabel("");
		lblShowInfo.setForeground(Color.RED);
		lblShowInfo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblShowInfo.setBounds(201, 107, 249, 32);
		add(lblShowInfo);

		JButton btnNewButton = new JButton("删除");
		btnNewButton.setIcon(new ImageIcon(DeleteOpencoursePane.class.getResource("/resources/assets/icon/tick.png")));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtCourseName.getText();
				if(id.isEmpty()) {
					lblShowInfo.setText("请输入要删除的公开课ID");
					return;
				}
				if(!SwingUtils.isPureDigits(id)) {
					lblShowInfo.setText("公开课ID只能为数字");
					return;
				}
				Boolean result = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.OpencourseManage.deleteOpencourse",
								new Object[] { Integer.parseInt(txtCourseName.getText()) }).send())
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "删除成功", "提示");
					lblShowInfo.setText("");
					txtCourseName.setText("");
					mainFrame.showOpenCourseList();
				} else {
					lblShowInfo.setText("删除失败，请检查所输入ID是否正确");
				}
			}
		});
		btnNewButton.setBounds(234, 158, 121, 57);
		add(btnNewButton);

		txtCourseName = new JTextField();
		txtCourseName.setBounds(201, 76, 201, 24);
		add(txtCourseName);
		txtCourseName.setColumns(10);
		
		

	}
}
