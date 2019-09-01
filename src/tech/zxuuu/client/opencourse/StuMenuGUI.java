package tech.zxuuu.client.opencourse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;

import javax.swing.JEditorPane;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;

public class StuMenuGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private JTextArea textArea;
	private JScrollPane spnCourseList;
	private JPanel pnlCourseList;
	
	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	
	//显示公开课列表
	private void showCourseList() {
		List<OpenCourseInfo> courseList =  ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.StuMenu.getCourseList",
				null).send()).getListReturn(OpenCourseInfo.class);
		
		for(OpenCourseInfo course : courseList) {
			pnlCourseList.add(new CourseInfoGUI(course.getId(), course.getCourseName(), course.getSpeaker(), course.getPreview()));
		}
	}

	/**
	 * Create the frame.
	 */
	public StuMenuGUI() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		frameWidth = 1680;
		frameHeight = 1280;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2, frameWidth, frameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBack = new JButton("返回");
		btnBack.setFont(new Font("宋体", Font.PLAIN, 30));
		btnBack.setBounds(40, 36, 247, 95);
		contentPane.add(btnBack);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 28));
		textArea.setText("这里是个人信息\r\n这里也是个人信息\r\n");
		textArea.setBounds(1405, 21, 228, 95);
		contentPane.add(textArea);
		
		spnCourseList = new JScrollPane();
		spnCourseList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spnCourseList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spnCourseList.setBounds(226, 201, 1255, 893);
		contentPane.add(spnCourseList);
		
		pnlCourseList = new JPanel();
		spnCourseList.setViewportView(pnlCourseList);
		pnlCourseList.setLayout(new GridLayout(0, 1, 0, 0));
		
		new Thread(new Runnable() {
			public void run() {
				showCourseList();
				try {
					wait(2000);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				pnlCourseList.paintImmediately(pnlCourseList.getBounds());
			}
		}).start();
		//showCourseList();
	}
}
