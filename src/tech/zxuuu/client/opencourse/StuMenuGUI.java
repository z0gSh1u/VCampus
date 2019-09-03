package tech.zxuuu.client.opencourse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class StuMenuGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane spnCourseList;
	private JPanel pnlCourseList;

	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;
	private JLabel label;
	private JLabel lblVcampus;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StuMenuGUI frame = new StuMenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 显示公开课列表
	private void showCourseList() {
		List<OpenCourseInfo> courseList = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.StuMenu.getCourseList", null).send())
				.getListReturn(OpenCourseInfo.class);

		for (OpenCourseInfo course : courseList) {
			pnlCourseList.add(new CourseInfoPane(course.getId(), course.getCourseName(), course.getSpeaker(),
					course.getPreview(), course.getVideo()));
		}
	}

	/**
	 * Create the frame.
	 */
	public StuMenuGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StuMenuGUI.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("在线课堂 - VCampus");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		frameWidth = 800;
		frameHeight = 600;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, 922, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		spnCourseList = new JScrollPane();
		spnCourseList.setBounds(52, 95, 813, 598);
		contentPane.add(spnCourseList);
		spnCourseList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spnCourseList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		pnlCourseList = new JPanel();
		spnCourseList.setViewportView(pnlCourseList);
		pnlCourseList.setLayout(new GridLayout(0, 1, 0, 0));
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(StuMenuGUI.class.getResource("/resources/assets/icon/opencourse.png")));
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);
		
		lblVcampus = new JLabel("在线课堂 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 239, 34);
		contentPane.add(lblVcampus);
		
		lblNewLabel = new JLabel("选择一门课程以开始...");
		lblNewLabel.setBounds(706, 64, 159, 18);
		contentPane.add(lblNewLabel);

		new Thread(new Runnable() {
			@Override
			public void run() {
				showCourseList();
				try {
					wait(2000);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				pnlCourseList.paintImmediately(pnlCourseList.getBounds());
			}
		}).start();

	}
}
