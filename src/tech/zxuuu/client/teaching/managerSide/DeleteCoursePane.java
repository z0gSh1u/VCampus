package tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;

import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;

public class DeleteCoursePane extends JPanel {
	private JTextField txtAca;
	private JTextField txtCourseId;
	private JTextField txtTime;
	private JTextField txtTeacherId;
	private JTextField txtClassroom;
	private JTextField txtName;
	private JTextField dispTime;
	private JTextField dispClassroom;
	private JTextField dispTeacherId;
	private JTextField dispTeacher;
	private JTextField dispAca;

	/**
	 * Create the panel.
	 */
	public DeleteCoursePane() {
		
		setLayout(null);

		JLabel lblNewLabel = new JLabel("删除课程");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(292, 32, 72, 18);
		add(lblNewLabel);

		JLabel label = new JLabel("课程编号");
		label.setBounds(58, 119, 72, 18);
		add(label);

		JLabel label_1 = new JLabel("课程名");
		label_1.setBounds(58, 203, 72, 18);
		add(label_1);

		JLabel lblNewLabel_1 = new JLabel("课程时间");
		lblNewLabel_1.setBounds(58, 249, 72, 18);
		add(lblNewLabel_1);

		txtAca = new JTextField();
		txtAca.setBounds(147, 116, 86, 24);
		add(txtAca);
		txtAca.setColumns(10);

		txtCourseId = new JTextField();
		txtCourseId.setBounds(247, 116, 86, 24);
		add(txtCourseId);
		txtCourseId.setColumns(10);

		txtTime = new JTextField();
		txtTime.setBounds(347, 116, 86, 24);
		add(txtTime);
		txtTime.setColumns(10);

		txtTeacherId = new JTextField();
		txtTeacherId.setBounds(448, 116, 86, 24);
		add(txtTeacherId);
		txtTeacherId.setColumns(10);

		txtClassroom = new JTextField();
		txtClassroom.setBounds(548, 116, 86, 24);
		add(txtClassroom);
		txtClassroom.setColumns(10);

		JLabel label_2 = new JLabel("教室");
		label_2.setBounds(58, 293, 72, 18);
		add(label_2);

		JLabel label_3 = new JLabel("教师代号");
		label_3.setBounds(58, 342, 72, 18);
		add(label_3);

		JLabel label_4 = new JLabel("教师");
		label_4.setBounds(58, 389, 72, 18);
		add(label_4);

		JLabel label_5 = new JLabel("开课院系");
		label_5.setBounds(147, 85, 72, 18);
		add(label_5);

		JLabel label_6 = new JLabel("系课程号");
		label_6.setBounds(247, 85, 72, 18);
		add(label_6);

		JLabel lblNewLabel_2 = new JLabel("课程时间");
		lblNewLabel_2.setBounds(343, 85, 72, 18);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("教师代号");
		lblNewLabel_3.setBounds(445, 85, 72, 18);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("教室");
		lblNewLabel_4.setBounds(548, 85, 72, 18);
		add(lblNewLabel_4);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(144, 200, 289, 24);
		add(txtName);
		txtName.setColumns(10);

		dispTime = new JTextField();
		dispTime.setEditable(false);
		dispTime.setBounds(147, 246, 286, 24);
		add(dispTime);
		dispTime.setColumns(10);

		dispClassroom = new JTextField();
		dispClassroom.setEditable(false);
		dispClassroom.setBounds(147, 290, 286, 24);
		add(dispClassroom);
		dispClassroom.setColumns(10);

		dispTeacherId = new JTextField();
		dispTeacherId.setEditable(false);
		dispTeacherId.setBounds(147, 339, 286, 24);
		add(dispTeacherId);
		dispTeacherId.setColumns(10);

		dispTeacher = new JTextField();
		dispTeacher.setEditable(false);
		dispTeacher.setBounds(144, 386, 286, 24);
		add(dispTeacher);
		dispTeacher.setColumns(10);

		JButton btnAutoLoad;
		JButton btnNewButton = new JButton("删除");
		btnNewButton.setIcon(new ImageIcon(NewCoursePane.class.getResource("/resources/assets/icon/tick.png")));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dispTeacher.getText().equals("")) {
					SwingUtils.showError(null, "请先通过自动装填！", "错误");
					return;
				}
				String ID = txtAca.getText() +"0"+ txtCourseId.getText() + txtTime.getText() + txtTeacherId.getText()
						+ txtClassroom.getText();
				Boolean oci = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.teaching.CourseManagerSide.deleteCourse", new Object[] { ID }).send())
						.getReturn(Boolean.class);
			}
		});
		btnNewButton.setBounds(342, 423, 115, 57);
		add(btnNewButton);

		JLabel lblNewLabel_5 = new JLabel("开课院系");
		lblNewLabel_5.setBounds(58, 162, 72, 18);
		add(lblNewLabel_5);

		dispAca = new JTextField();
		dispAca.setEditable(false);
		dispAca.setColumns(10);
		dispAca.setBounds(147, 163, 286, 24);
		add(dispAca);

		btnAutoLoad = new JButton("自动装填");
		btnAutoLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAca.getText().length() != 2) {
					SwingUtils.showError(null, "院系代码长度错误！", "错误");
					return;
				}
				if (txtClassroom.getText().length() != 4) {
					SwingUtils.showError(null, "教室代码长度错误！", "错误");
					return;
				}
				if (txtCourseId.getText().length() != 3) {
					SwingUtils.showError(null, "院系内课程代码长度错误！", "错误");
					return;
				}
				if (txtTeacherId.getText().length() != 3) {
					SwingUtils.showError(null, "院系内教师代码长度错误！", "错误");
					return;
				}
				if (txtTime.getText().length() != 6) {
					SwingUtils.showError(null, "课程时间长度错误！", "错误");
					return;
				}
				if (SwingUtils.isTxtEmpty(txtAca) || SwingUtils.isTxtEmpty(txtClassroom) || SwingUtils.isTxtEmpty(txtCourseId)
						|| SwingUtils.isTxtEmpty(txtTeacherId) || SwingUtils.isTxtEmpty(txtTime)) {
					SwingUtils.showError(null, "课程编号不完整！", "错误");
					return;
				}

				String academyName = OtherUtils.getAcademyByNumber(txtAca.getText());
				if (academyName == "") {
					SwingUtils.showError(null, "查无此院系！", "错误");
					dispAca.setText("");
					return;
				}
				dispAca.setText(txtAca.getText() + " - " + academyName);
				dispClassroom.setText("教" + txtClassroom.getText().charAt(0) + "-" + txtClassroom.getText().substring(1));
				dispTeacherId.setText(txtTeacherId.getText());				
				
				String ID = txtAca.getText() +"0"+ txtCourseId.getText() + txtTime.getText() + txtTeacherId.getText()
				+ txtClassroom.getText();
				ClassInfo cla = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass",
								new Object[] { ID }).send())
						.getReturn(ClassInfo.class);
				txtName.setText(cla.getClassName());
				dispTeacher.setText(cla.getTeacher());
				dispTime.setText(cla.getTime());
				dispTeacher.setText(cla.getTeacher());
				dispClassroom.setText(cla.getClassroom());
			}
		});
		btnAutoLoad.setBounds(177, 434, 113, 27);
		add(btnAutoLoad);

	}

	public static String convertClassTimeToChinese(String classTimeCode) {
		String[] parts = new String[2];
		parts[0] = classTimeCode.substring(0, 3);
		parts[1] = classTimeCode.substring(3, 6);
		String result = "";
		for (String string : parts) {
			result += "周";
			result += string.charAt(0) == '1' ? "一"
					: string.charAt(0) == '2' ? "二"
							: string.charAt(0) == '3' ? "三"
									: string.charAt(0) == '4' ? "四" : string.charAt(0) == '5' ? "五" : "[ERROR]";
			result += string.charAt(1);
			result += '-';
			result += string.charAt(2);
			result += "节；";
		}
		return result;

		
	}

}
