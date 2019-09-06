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
import tech.zxuuu.client.opencourse.NewOpencoursePane;
import tech.zxuuu.client.opencourse.StuMenuGUI;
import tech.zxuuu.client.opencourse.TeacherNewOpencoursePane;

public class AppTeacher extends JFrame {

	private JPanel contentPane;
	private JPanel currentDisplay;
	private JPanel coursePanel;
	private ListOpencoursePane listOpencoursePane;
	private JPanel newOpencoursePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppTeacher frame = new AppTeacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void updateOpenCourseList() {
		this.listOpencoursePane.updateOpenCourse();
	}
	/**
	 * Create the frame.
	 */
	public AppTeacher() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppTeacher.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("教师端 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1046, 791);
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

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setBounds(440, 46, 559, 18);
		contentPane.add(lblWelcome);

		lblWelcome.setText(
				"欢迎您， " + App.session.getTeacher().getName() + " 老师！您的一卡通号：" + App.session.getTeacher().getCardNumber());
		
		JPanel pnlOpencourse = new JPanel();
		newOpencoursePanel = pnlOpencourse;
		pnlOpencourse.setBounds(14, 70, 837, 650);
		contentPane.add(pnlOpencourse);
		pnlOpencourse.setLayout(null);
		pnlOpencourse.setVisible(false);
		
		listOpencoursePane = new ListOpencoursePane();
		listOpencoursePane.setBounds(21, 31, 795, 347);
		pnlOpencourse.add(listOpencoursePane);
		
		TeacherNewOpencoursePane teacherNewOpencoursePane = new TeacherNewOpencoursePane(App.session.getTeacher().getName(), this);
		teacherNewOpencoursePane.setBounds(21, 376, 795, 274);
		pnlOpencourse.add(teacherNewOpencoursePane);
		
		JPanel pnlCourseList = new JPanel();
		currentDisplay = coursePanel = pnlCourseList;
		pnlCourseList.setBounds(14, 70, 837, 650);
		contentPane.add(pnlCourseList);
		pnlCourseList.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("排课表");
		lblNewLabel.setBounds(384, 0, 54, 24);
		pnlCourseList.add(lblNewLabel);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JPanel panel = new ScheduleTablePane();
		panel.setBounds(0, 34, 837, 616);
		pnlCourseList.add(panel);
		
		JButton btnCourseList = new JButton("查看排课表");
		btnCourseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentDisplay.setVisible(false);
				coursePanel.setVisible(true);
				currentDisplay = coursePanel;
			}
		});
		btnCourseList.setBounds(858, 109, 153, 37);
		contentPane.add(btnCourseList);
		
		JButton btnOpencourse = new JButton("管理公开课");
		btnOpencourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newOpencoursePanel.setVisible(true);
				currentDisplay = newOpencoursePanel;
			}
		});
		btnOpencourse.setBounds(858, 167, 153, 37);
		contentPane.add(btnOpencourse);
		
		JButton btnGotoOpencourse = new JButton("前往公开课");
		btnGotoOpencourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuMenuGUI stuMenuGUI = new StuMenuGUI();
				stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stuMenuGUI.setVisible(true);
			}
		});
		btnGotoOpencourse.setBounds(858, 224, 153, 37);
		contentPane.add(btnGotoOpencourse);
	}
}
