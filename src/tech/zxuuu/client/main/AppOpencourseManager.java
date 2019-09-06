package tech.zxuuu.client.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.opencourse.DeleteOpencoursePane;
import tech.zxuuu.client.opencourse.ListOpencoursePane;
import tech.zxuuu.client.opencourse.NewOpencoursePane;
import tech.zxuuu.client.opencourse.StuMenuGUI;
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
	private ListOpencoursePane listOpencoursePane;
	private JPanel deleteOpencoursePane;
	private JPanel newOpencoursePane;
	private JPanel defaultPane;

	/**
	 * Create the frame.
	 */
	public void showOpenCourseList() {
		currentDisplay.setVisible(false);
		listOpencoursePane.updateOpenCourse();
		listOpencoursePane.setVisible(true);
		currentDisplay = listOpencoursePane;
	}
	
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

		JLabel lblCardDisp = new JLabel((String) null);
		lblCardDisp.setBounds(488, 48, 114, 18);
		contentPane.add(lblCardDisp);
		lblCardDisp.setText(App.session.getManager().getCardNumber());

		JButton btnList = new JButton("公开课列表");
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOpenCourseList();
			}
		});

		btnList.setBounds(607, 25, 105, 53);

		contentPane.add(btnList);

		JButton btnNew = new JButton("新增公开课");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newOpencoursePane.setVisible(true);
				currentDisplay = newOpencoursePane;
			}
		});

		btnNew.setBounds(719, 13, 105, 34);

		contentPane.add(btnNew);

		JButton btnDelete = new JButton("删除公开课");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				deleteOpencoursePane.setVisible(true);
				currentDisplay = deleteOpencoursePane;
			}
		});

		btnDelete.setBounds(719, 50, 105, 34);

		contentPane.add(btnDelete);

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

		newOpencoursePane = new NewOpencoursePane(this);
		newOpencoursePane.setBounds(14, 90, 925, 539);
		newOpencoursePane.setVisible(false);
		contentPane.add(newOpencoursePane);
		
		deleteOpencoursePane = new DeleteOpencoursePane(this);
		deleteOpencoursePane.setBounds(14, 90, 925, 539);
		deleteOpencoursePane.setVisible(false);
		contentPane.add(deleteOpencoursePane);

		currentDisplay = defaultPane;
		
		JButton btnShowOpenCourseList = new JButton("进入公开课");
		btnShowOpenCourseList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StuMenuGUI stuMenuGUI = new StuMenuGUI();
				stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stuMenuGUI.setVisible(true);
			}
		});
		btnShowOpenCourseList.setBounds(831, 24, 113, 53);
		contentPane.add(btnShowOpenCourseList);

	}
}
