package tech.zxuuu.client.opencourse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.client.main.App;
import tech.zxuuu.net.Session;
import tech.zxuuu.entity.Student;
import tech.zxuuu.entity.UserType;
import tech.zxuuu.server.opencourse.ChatManager;
import tech.zxuuu.server.opencourse.ChatSocket;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class StuCourseGUI extends JFrame {
	
	private JPanel contentPane;
	private JEditorPane epnInputBox;
	private JEditorPane epnChatBox;
	
	private int screenWidth;
	private int screenHeight;
	private int frameWidth;
	private int frameHeight;
	private int courseId;
	private ChatSocket chatSocket;
	
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
	
	private void updateMessage(String str, ChatSocket listener) {
		System.out.println("[StuCourseGUI]收到新信息:"+str);
		String text = epnChatBox.getText();
		int s = text.indexOf("<body>");
		int f = text.lastIndexOf("</body>");
		text = text.substring(s+11, f-3);
		epnChatBox.setText(text+str);
		epnChatBox.grabFocus();
		epnInputBox.grabFocus();
	}
	
	private Boolean sendMessage(String str) {
		
		if(str.isEmpty())
			return false;
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("\r", "");
		
		this.chatSocket.write(str);
		return true;
	}
	
	private Boolean connect() {
		try{
			new Thread((this.chatSocket = new ChatSocket(new Socket("127.0.0.1", 1984), (String str, ChatSocket chatSocket)->updateMessage(str, chatSocket)))).start();
			this.chatSocket.setUserType(App.session.getUserType());
			switch(this.chatSocket.getUserType()) {
				case STUDENT:
					this.chatSocket.setName(App.session.getStudent().getName());
					break;
				case TEACHER:
					this.chatSocket.setName(App.session.getTeacher().getName());
					break;
				case MANAGER:
					this.chatSocket.setName(App.session.getManager().getCardNumber());
					break;
			}
			this.chatSocket.setCourseId(courseId);
			this.chatSocket.write(Integer.toString(this.courseId)+"\\"+JSON.toJSONString(this.chatSocket.getUserType())+"\\"+this.chatSocket.getName());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private void init() {
		//测试用初始化
		this.courseId = 1;
		
		connect();
		
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
		
		epnInputBox = new JEditorPane();
		epnInputBox.setBounds(260, 897, 1083, 145);
		contentPane.add(epnInputBox);
		
		epnChatBox = new JEditorPane();
		epnChatBox.setEditable(false);
		epnChatBox.setContentType("text/html");
		epnChatBox.setBounds(260, 83, 1083, 722);
		contentPane.add(epnChatBox);
		
		
		JButton btnSendMess = new JButton("发送");
		btnSendMess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(epnInputBox.getText());
				epnInputBox.setText("");
			}
		});
		epnInputBox.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(epnInputBox.getText());
					epnInputBox.setText("");
				}
			}
		});
		btnSendMess.setBounds(47, 959, 153, 37);
		contentPane.add(btnSendMess);
		
		JPanel pnlEmoticon = new JPanel();
		pnlEmoticon.setBounds(1370, 766, 303, 283);
		contentPane.add(pnlEmoticon);
		pnlEmoticon.setLayout(new GridLayout(0, 10, 0, 0));
		
		/*
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setBounds(710, 205, 720, 813);
		editorPane.setText("hhh<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAALhSURBVGhD7ZhJSFVRGICfDUa2anBThosysKKJoKKg2jQgaGAFCZHWwoW1CXJVroIQomlXuyQbFgmBSQS1iKCEsAFKaCCEt8gGgwrT1Or77znB8033nON9+qTzwcd/7n/vGf777rv33RfzeDwej8fj8XjCKNBxFJ9LFm8g1OICLMYv+Alb5sXf3iPmHSmFUMRJQg1ewDcoBczFMjyA07GRgu4Q8xOKKMCPWK5TKbCvGnvwjE7lBVN0/MdefMDZ7labqbDvJmE5llGMfGrO0P8i9uFjrNBpJ5ILWY1PVTMzFPOdUIXlLOBUkLSEficIcrkuwrPYTO4KzqdtTXIhq/CZamaHYn4T9mMDk68JkoZw/C5CHdYwzle8gfIpv8dX7D9EdIcBem3PCMcfwXa9GQrHFmMcd+rUKMivwxd4TqfsoGMpxvWmFfTrQjnLoXDcdZQ7Y0bYX4TX8JZOhZJ4aa3A56ppjVzvA6qZGRZ2kFDKZXRcZdLD/n7cR/MDfdox7fMukcRCluFL1bSDSW9j1ucKi1lIkFv20SBhAGPWE3qwg/5Tg2QGEgtZik6FGPIHm1jcI7VpBsc3EF6j2feQilvR6u4znrC2q3heb05eKKIQn+BmnfJ4PJ7/BW59WZ+uUcE863XTmOSf8RlhcDn2F7FJZXKK9cPZuBB+Ksj7h7wILWGSbqwOdkQM4+4g9DJfl8rkECarwAGs1KnIYMwWPKw3cw+TbcMRTPuC5AJjzcZhnKNTxhhfWsnw0d8l7MHTQSIa5H2llbH71KY5zoUITNhG6OQMNqvMmJFCLqumHWMqRHMMqyhGFuEM/RsJ7zg591VmAmARa1G+/Nt1ygr6bcVBXKlTEweLqMQhlO+NERw7C2tRbhryju5M6Eu9DSxmC0H+iezEh/gDh1F+Ech/xjNwJhZhCe5GeYW9xCXVQXQm0kIEipE/vDfhRizEaTiCQziIP7Efv2Gbyx3K4/F4Jjux2F83SP2Ij+FUYgAAAABJRU5ErkJggg==\" /> ");
		String str = editorPane.getText();//https://s2.ax1x.com/2019/08/23/mB1HRf.jpg
		int s = str.indexOf("<body>");
		int f = str.lastIndexOf("</body>");
		str = str.substring(s+11, f-3);
		//System.out.println(str);
		contentPane.add(editorPane);
		//System.out.println(editorPane.getText());
		editorPane.setText(str + "233<img src=\"https://s2.ax1x.com/2019/08/23/mB1bz8.jpg\" alt=\"mB1bz8.jpg\" border=\"0\" />");
		*/
		init();
	}
}
