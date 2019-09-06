package tech.zxuuu.client.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import tech.zxuuu.client.library.LibraryStudentGUI;
import tech.zxuuu.client.library.MyBorrowGUI;
import tech.zxuuu.client.opencourse.StuMenuGUI;
import tech.zxuuu.client.shop.ShopFirstPage;
import tech.zxuuu.client.teaching.studentSide.TeachingStudentMain;
import tech.zxuuu.entity.Book;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.NoticeInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AppStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AppStudent() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppStudent.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("学生主页 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 800);
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
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						TeachingStudentMain teachingStudentMain = new TeachingStudentMain();
						teachingStudentMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						teachingStudentMain.setVisible(true);
					}
				});
			}
		});
		btnTeaching.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnTeaching.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/teaching.png")));
		btnTeaching.setBounds(374, 600, 220, 80);
		contentPane.add(btnTeaching);

		JButton btnLibrary = new JButton(" 李文正图书馆");
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
		btnLibrary.setBounds(120, 491, 220, 80);
		contentPane.add(btnLibrary);

		JButton btnShop = new JButton("天平超市");
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
		btnShop.setBounds(120, 600, 220, 80);
		contentPane.add(btnShop);

		JButton btnOpencourse = new JButton("在线课堂");
		btnOpencourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						StuMenuGUI stuMenuGUI = new StuMenuGUI();
						stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						stuMenuGUI.setVisible(true);
					}
				});
			}
		});
		btnOpencourse.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/opencourse.png")));
		btnOpencourse.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnOpencourse.setBounds(374, 491, 220, 80);
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
		lblNewLabel_1.setBounds(806, 143, 122, 64);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(135, 206, 250), 1, true));
		panel.setBounds(806, 221, 280, 216);
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
		lblNewLabel_2.setBounds(806, 450, 64, 64);
		contentPane.add(lblNewLabel_2);

		JLabel lblToday = new JLabel("今天是星期");
		lblToday.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblToday.setBounds(884, 460, 198, 18);
		contentPane.add(lblToday);

		/**
		 * 新增，初始化控件内容
		 */
		lblName.setText(App.session.getStudent().getName());
		lblAcademy.setText(App.session.getStudent().getAcademy() + " - "
				+ OtherUtils.getAcademyByNumber(App.session.getStudent().getAcademy()));
		lblBalance.setText(String.format("%.2f", App.session.getStudent().getBalance()));
		lblStudentNumber.setText(App.session.getStudent().getStudentNumber());
		lblCardNumber.setText(App.session.getStudent().getCardNumber());
		List<Book> tempList = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Addons.getBorrowedBookList",
						new Object[] { App.session.getStudent().getCardNumber() }).send())
				.getListReturn(Book.class);
		if (tempList == null || tempList.size() == 0) {
			lblBookLend.setText("0 本");
		} else {
			lblBookLend.setText(tempList.size() + " 本");
		}
		JButton btnRecharge = new JButton("充值");
		btnRecharge.addActionListener(new ActionListener() {
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
					lblBalance
							.setText(String.format("%.2f", Double.parseDouble(lblBalance.getText()) + Double.parseDouble(money)));
				} else {
					SwingUtils.showError(null, "充值失败！", "错误");
				}

			}
		});
		btnRecharge.setBounds(203, 147, 63, 27);
		panel.add(btnRecharge);

		JButton btnMyBorrow = new JButton("查看");
		btnMyBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyBorrowGUI myBorrowGUI = new MyBorrowGUI();
				myBorrowGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myBorrowGUI.setVisible(true);
			}
		});
		btnMyBorrow.setBounds(203, 174, 63, 27);
		panel.add(btnMyBorrow);

		Integer today = OtherUtils.getDay(ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
						.send())
				.getReturn(Long.class));
		// Integer today = 2; // Test
		lblToday.setText("今天是星期" + (today == 1 ? "一"
				: today == 2 ? "二"
						: today == 3 ? "三" : today == 4 ? "四" : today == 5 ? "五" : today == 6 ? "六" : today == 7 ? "日" : "..."));

		/* 计算今日课程 */
		JLabel lblTodayCourses = new JLabel("今日课程：");
		String selectedClass = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
						new Object[] { App.session.getStudent() }).send())
				.getReturn(String.class);

		String[] course = selectedClass.split(",");

		ClassInfo[] todayClass = new ClassInfo[5];
		for (int i = 0; i < 5; i++) {
			todayClass[i] = null;
		}

		if (selectedClass != "" && !selectedClass.equals("")) {
			for (int i = 0; i < course.length; i++) {
				if (Integer.valueOf(course[i].charAt(6) - 48) == today) {
					ClassInfo cla = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
							.getReturn(ClassInfo.class);
					todayClass[(Integer.valueOf(course[i].charAt(8)) - 48) / 2 - 1] = cla;
				}
				if (Integer.valueOf(course[i].charAt(9) - 48) == today) {
					ClassInfo cla = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
							.getReturn(ClassInfo.class);
					todayClass[(Integer.valueOf(course[i].charAt(11)) - 48) / 2 - 1] = cla;
				}

			}
		}

		JLabel lblCI1 = new JLabel("无课程");
		lblCI1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		JLabel lblCI2 = new JLabel("无课程");
		lblCI2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		JLabel lblCI3 = new JLabel("无课程");
		lblCI3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		JLabel lblCI4 = new JLabel("无课程");
		lblCI4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		JLabel lblCI5 = new JLabel("无课程");
		lblCI5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		JLabel[] lblCourses = new JLabel[] { lblCI1, lblCI2, lblCI3, lblCI4, lblCI5 };

		for (int i = 0; i < 5; i++) {
			if (todayClass[i] != null) {
				lblCourses[i].setText(todayClass[i].getClassName() + " @ " + todayClass[i].getClassroom());
			}
		}

		lblTodayCourses.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblTodayCourses.setBounds(884, 491, 115, 18);
		contentPane.add(lblTodayCourses);

		JLabel lblNewLabel_3 = new JLabel("1~2节");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(806, 541, 64, 24);
		contentPane.add(lblNewLabel_3);

		JLabel label_6 = new JLabel("3~4节");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_6.setBounds(806, 578, 64, 24);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("5~6节");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_7.setBounds(806, 615, 64, 24);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("7~8节");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_8.setBounds(806, 652, 64, 24);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("9~10节");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_9.setBounds(806, 689, 64, 24);
		contentPane.add(label_9);

		lblCI1.setBounds(884, 546, 222, 18);
		contentPane.add(lblCI1);

		lblCI2.setBounds(884, 583, 222, 18);
		contentPane.add(lblCI2);

		lblCI3.setBounds(884, 620, 222, 18);
		contentPane.add(lblCI3);

		lblCI4.setBounds(884, 657, 222, 18);
		contentPane.add(lblCI4);

		lblCI5.setBounds(884, 694, 222, 18);
		contentPane.add(lblCI5);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/picture/mainbanner.png")));
		lblNewLabel_4.setBounds(418, 0, 730, 130);
		contentPane.add(lblNewLabel_4);

		List<NoticeInfo> noticeInfos = ResponseUtils
				.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTop4Notice", null).send())
				.getListReturn(NoticeInfo.class);

		NoticeBlock noticeBlock1 = new NoticeBlock(noticeInfos.get(0).getTitle(),
				quickFormatDate(noticeInfos.get(0).getDate()), noticeInfos.get(0).getUrl());
		noticeBlock1.setBounds(57, 170, 660, 50);
		contentPane.add(noticeBlock1);

		NoticeBlock noticeBlock2 = new NoticeBlock(noticeInfos.get(1).getTitle(),
				quickFormatDate(noticeInfos.get(1).getDate()), noticeInfos.get(1).getUrl());
		noticeBlock2.setBounds(57, 233, 660, 50);
		contentPane.add(noticeBlock2);

		NoticeBlock noticeBlock3 = new NoticeBlock(noticeInfos.get(2).getTitle(),
				quickFormatDate(noticeInfos.get(2).getDate()), noticeInfos.get(2).getUrl());
		noticeBlock3.setBounds(57, 296, 660, 50);
		contentPane.add(noticeBlock3);

		NoticeBlock noticeBlock4 = new NoticeBlock(noticeInfos.get(3).getTitle(),
				quickFormatDate(noticeInfos.get(3).getDate()), noticeInfos.get(3).getUrl());
		noticeBlock4.setBounds(57, 359, 660, 50);
		contentPane.add(noticeBlock4);

	}

	protected String quickFormatDate(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}
}
