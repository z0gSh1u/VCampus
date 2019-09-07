package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.teaching.teacherSide.ScheduleTablePane;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import tech.zxuuu.client.opencourse.ListOpencoursePane;
import tech.zxuuu.client.opencourse.StuMenuGUI;
import tech.zxuuu.client.opencourse.TeacherNewOpencoursePane;

public class AppTeacher extends JFrame {

	private JPanel contentPane;
	private JPanel currentDisplay;
	private JPanel coursePanel;
	private ListOpencoursePane listOpencoursePane;
	private JPanel newOpencoursePanel;

	public void updateOpenCourseList() {
		this.listOpencoursePane.updateOpenCourse();
	}

	/**
	 * Create the frame.
	 */
	public AppTeacher() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppTeacher.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("教师端 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 967, 801);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppTeacher.class.getResource("/resources/assets/icon/teacher.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("教师主页 - VCampus");
		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		lblVcampus.setBounds(92, 26, 306, 43);
		contentPane.add(lblVcampus);

		coursePanel = new ScheduleTablePane();
		coursePanel.setBounds(14, 100, 932, 650);
		contentPane.add(coursePanel);

		currentDisplay = coursePanel;
		coursePanel.setVisible(true);

		JPanel pnlOpencourse = new JPanel();
		newOpencoursePanel = pnlOpencourse;
		pnlOpencourse.setBounds(14, 100, 932, 650);
		contentPane.add(pnlOpencourse);
		pnlOpencourse.setLayout(null);
		pnlOpencourse.setVisible(false);

		listOpencoursePane = new ListOpencoursePane();
		listOpencoursePane.setBounds(14, 31, 904, 347);
		pnlOpencourse.add(listOpencoursePane);

		TeacherNewOpencoursePane teacherNewOpencoursePane = new TeacherNewOpencoursePane(App.session.getTeacher().getName(),
				this);
		teacherNewOpencoursePane.setBounds(14, 376, 904, 274);
		pnlOpencourse.add(teacherNewOpencoursePane);

		JButton btnCourseList = new JButton("查看排课表");
		btnCourseList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDisplay.setVisible(false);
				coursePanel.setVisible(true);
				currentDisplay = coursePanel;
			}
		});
		btnCourseList.setBounds(439, 58, 107, 27);
		contentPane.add(btnCourseList);

		JButton btnOpencourse = new JButton("管理公开课");
		btnOpencourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newOpencoursePanel.setVisible(true);
				currentDisplay = newOpencoursePanel;
			}
		});
		btnOpencourse.setBounds(671, 58, 107, 27);
		contentPane.add(btnOpencourse);

		JButton btnGotoOpencourse = new JButton("前往公开课");
		btnGotoOpencourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StuMenuGUI stuMenuGUI = new StuMenuGUI();
				stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stuMenuGUI.setVisible(true);
			}
		});
		btnGotoOpencourse.setBounds(555, 58, 107, 27);
		contentPane.add(btnGotoOpencourse);

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setBounds(440, 26, 416, 18);
		contentPane.add(lblWelcome);

		lblWelcome.setText(
				"欢迎您， " + App.session.getTeacher().getName() + " 老师！您的一卡通号：" + App.session.getTeacher().getCardNumber());

	}
}
