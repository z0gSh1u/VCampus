package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import org.apache.ibatis.jdbc.Null;

import com.sun.javafx.collections.SortableList;

import tech.zxuuu.client.library.LibraryStudentGUI;
import tech.zxuuu.client.opencourse.StuMenuGUI;
import tech.zxuuu.client.shop.ShopFirstPage;
import tech.zxuuu.client.teaching.studentSide.TeachingStudentMain;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AppStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppStudent frame = new AppStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	/**
	 * Create the frame.
	 */
	public AppStudent() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppStudent.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("学生主页 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setLocation(-871, -176);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVcampus = new JLabel("学生主页 - VCampus");
		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		lblVcampus.setBounds(103, 25, 306, 43);
		contentPane.add(lblVcampus);

		JButton btnTeaching = new JButton(" 教务平台");
		btnTeaching.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				TeachingStudentMain teachingStudentMain = new TeachingStudentMain();
				teachingStudentMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				teachingStudentMain.setVisible(true);

			}
		});
		btnTeaching.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnTeaching.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/teaching.png")));
		btnTeaching.setBounds(347, 281, 220, 80);
		contentPane.add(btnTeaching);

		JButton btnLibrary = new JButton(" 李文歪图书馆");
		btnLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryStudentGUI libraryStudentGUI = new LibraryStudentGUI();
				libraryStudentGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				libraryStudentGUI.setVisible(true);
			}
		});
		btnLibrary.setBackground(new Color(255, 255, 240));
		btnLibrary.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnLibrary.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/library.png")));
		btnLibrary.setBounds(103, 184, 220, 80);
		contentPane.add(btnLibrary);

		JButton btnShop = new JButton("天不平超市");
		btnShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShopFirstPage shopFirstPage = new ShopFirstPage();
				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopFirstPage.setVisible(true);
			}
		});
		btnShop.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnShop.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/shop.png")));
		btnShop.setBounds(103, 281, 220, 80);
		contentPane.add(btnShop);

		JButton btnOpencourse = new JButton("在线课堂");
		btnOpencourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StuMenuGUI stuMenuGUI = new StuMenuGUI();
				stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stuMenuGUI.setVisible(true);

			}
		});
		btnOpencourse.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/opencourse.png")));
		btnOpencourse.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnOpencourse.setBounds(347, 184, 220, 80);
		contentPane.add(btnOpencourse);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/app.png")));
		lblNewLabel.setBounds(25, 12, 64, 64);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("关于我");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/aboutme.png")));
		lblNewLabel_1.setBounds(871, 97, 122, 64);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(135, 206, 250), 1, true));
		panel.setBounds(871, 175, 280, 216);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("...");
		lblName.setBounds(104, 23, 149, 18);
		panel.add(lblName);

		JLabel lblCardNumber = new JLabel("...");
		lblCardNumber.setBounds(104, 54, 72, 18);
		panel.add(lblCardNumber);

		JLabel lblStudentNumber = new JLabel("...");
		lblStudentNumber.setBounds(104, 85, 149, 18);
		panel.add(lblStudentNumber);

		JLabel lblAcademy = new JLabel("...");
		lblAcademy.setBounds(104, 116, 149, 18);
		panel.add(lblAcademy);

		JLabel lblBalance = new JLabel("...");
		lblBalance.setBounds(104, 147, 97, 18);
		panel.add(lblBalance);

		JLabel lblBookLend = new JLabel("...");
		lblBookLend.setBounds(104, 178, 72, 18);
		panel.add(lblBookLend);

		JLabel label = new JLabel("姓名：");
		label.setBounds(18, 23, 72, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("一卡通号：");
		label_1.setBounds(18, 54, 84, 18);
		panel.add(label_1);

		JLabel label_2 = new JLabel("学号：");
		label_2.setBounds(18, 85, 72, 18);
		panel.add(label_2);

		JLabel label_3 = new JLabel("院系：");
		label_3.setBounds(18, 116, 72, 18);
		panel.add(label_3);

		JLabel label_4 = new JLabel("一卡通余额：");
		label_4.setBounds(18, 147, 104, 18);
		panel.add(label_4);

		JLabel label_5 = new JLabel("图书馆借书：");
		label_5.setBounds(18, 178, 104, 18);
		panel.add(label_5);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/test.png")));
		lblNewLabel_2.setBounds(871, 404, 64, 64);
		contentPane.add(lblNewLabel_2);

		JLabel lblToday = new JLabel("今天是星期");
		lblToday.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblToday.setBounds(949, 414, 198, 18);
		contentPane.add(lblToday);

		/**
		 * 新增，初始化控件内容
		 */
		lblName.setText(App.session.getStudent().getName());
		lblAcademy.setText(App.session.getStudent().getAcademy() + " - "
				+ OtherUtils.getAcademyByNumber(App.session.getStudent().getAcademy()));
		lblBalance.setText(String.format("%.2f", App.session.getStudent().getBalance()));
		lblStudentNumber.setText(App.session.getStudent().getStudentNumber());

		JButton btnNewButton = new JButton("充值");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String money = SwingUtils.popInput("请输入充值金额：");
				if (!SwingUtils.isPureDigits(money)) {
					SwingUtils.showError(null, "输入有误！", "错误");
					return;
				}

				Boolean result = ResponseUtils
						.getResponseByHash(
								(new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.chargeCardBalance",
										new Object[] { App.session.getStudent().getCardNumber(), Integer.parseInt(money) }).send()))
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "充值成功！", "提示");
				} else {
					SwingUtils.showError(null, "充值失败！", "错误");
				}

			}
		});
		btnNewButton.setBounds(203, 147, 63, 27);
		panel.add(btnNewButton);

		// TODO Uncomment this when release.
//		Integer today = OtherUtils.getDay(ResponseUtils.getResponseByHash(
//				new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
//						.send())
//				.getReturn(Long.class));
		Integer today = 3;
		lblToday.setText("今天是星期" + (today == 1 ? "一"
				: today == 2 ? "二"
						: today == 3 ? "三" : today == 4 ? "四" : today == 5 ? "五" : today == 6 ? "六" : today == 7 ? "日" : "..."));

		JLabel lblTodayCourses = new JLabel("今日课程：");
		String selectedClass=ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
						new Object[] {App.session.getStudent()}).send())
				.getReturn(String.class);
		
		String[] course=selectedClass.split(",");
		
		ClassInfo [] todayClass=new ClassInfo [5];
		for (int i=0;i<5;i++) {
			todayClass[i]=null;
		}
		
		for (int i=0;i<course.length;i++) {
			if (Integer.valueOf(course[i].charAt(6)-48)==today) {
				ClassInfo cla=ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass",
								new Object[] {course[i]}).send())
						.getReturn(ClassInfo.class);
				todayClass[(Integer.valueOf(course[i].charAt(8))-48)/2-1]=cla;
			}
			if (Integer.valueOf(course[i].charAt(9)-48)==today) {
				ClassInfo cla=ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass",
								new Object[] {course[i]}).send())
						.getReturn(ClassInfo.class);
				todayClass[(Integer.valueOf(course[i].charAt(11))-48)/2-1]=cla;
			}
		}
		
		for (int i=0;i<5;i++) {
			if (todayClass[i]!=null)
			{
			   System.out.println(i);
			   System.out.println(todayClass[i].getClassName()+"  "+todayClass[i].getTime());
			}
		}
		
		lblTodayCourses.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblTodayCourses.setBounds(949, 445, 115, 18);
		contentPane.add(lblTodayCourses);

	}
}
