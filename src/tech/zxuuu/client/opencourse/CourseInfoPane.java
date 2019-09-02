package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseInfoPane extends JPanel {

	private int id;// 课程ID
	private String preview;// 预览图链接
	private String courseName;// 课程名称
	private String speaker;// 主讲人名称
	private JEditorPane edpPreview;
	private JLabel lblCourseName;
	private JLabel lblSpeaker;
	public String videoUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
	 * Create the panel.
	 */
	public CourseInfoPane() {
		setLayout(null);

		this.edpPreview = new JEditorPane();
		edpPreview.setContentType("text/html");
		edpPreview.setBounds(0, 0, 121, 121);
		add(edpPreview);

		this.lblCourseName = new JLabel("这里是课程名");
		lblCourseName.setFont(new Font("宋体", Font.PLAIN, 50));
		lblCourseName.setBounds(122, 0, 477, 58);
		add(lblCourseName);

		this.lblSpeaker = new JLabel("这里是主讲人");
		lblSpeaker.setFont(new Font("宋体", Font.PLAIN, 35));
		lblSpeaker.setBounds(122, 80, 465, 41);
		add(lblSpeaker);

		JButton btnNewButton = new JButton("进入");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("vidhere=" + videoUrl);
				StuCourseGUI stuCourseGUI = new StuCourseGUI(id, videoUrl);
				stuCourseGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stuCourseGUI.setVisible(true);
			}
		});
		btnNewButton.setBounds(608, 80, 99, 41);
		add(btnNewButton);
	}

	public CourseInfoPane(int id, String courseName, String speaker, String preview, String videoUrl) {
		this();
		this.id = id;
		this.preview = preview;
		this.courseName = courseName;
		this.speaker = speaker;
		this.videoUrl = videoUrl;
		this.edpPreview.setText(this.preview);
		this.lblCourseName.setText(this.courseName);
		this.lblSpeaker.setText(this.speaker);
	}
}
