package tech.zxuuu.client.opencourse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StuCourseGUI extends JFrame {

	private JPanel contentPane;
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
					StuCourseGUI frame = new StuCourseGUI();
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
	public StuCourseGUI() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		frameWidth = 1720;
		frameHeight = 1160;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2, frameWidth, frameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setBounds(710, 205, 720, 813);
		editorPane.setText("hhh<img src=\"https://s2.ax1x.com/2019/08/23/mB1HRf.jpg\" alt=\"mB1HRf.jpg\" border=\"0\" />");
		String str = editorPane.getText();
		int s = str.indexOf("<body>");
		int f = str.lastIndexOf("</body>");
		str = str.substring(s+11, f-3);
		//System.out.println(str);
		contentPane.add(editorPane);
		//System.out.println(editorPane.getText());
		editorPane.setText(str + "233<img src=\"https://s2.ax1x.com/2019/08/23/mB1bz8.jpg\" alt=\"mB1bz8.jpg\" border=\"0\" />");
	}
}
