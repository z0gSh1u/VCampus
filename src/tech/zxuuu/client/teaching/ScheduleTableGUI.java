package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.entity.*;

public class ScheduleTableGUI extends JFrame {

	private JPanel contentPane;
	private JLabel[] labels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleTableGUI frame = new ScheduleTableGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public List<ClassInfo> getClassOfOneTeacher(String name) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassOfOneTeacher",
				new Object[] {name});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp =ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(ClassInfo.class);
	}

	public String getClassSeletion(Student student) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
				new Object[] {student});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp =ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(String.class);
	}
	
	public ClassInfo getOneClass(String ID) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass",
				new Object[] {ID});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp =ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(ClassInfo.class);
	}
	
	
	public void studentSchedule(){
	    Student stu=App.session.getStudent();
	    String[] course=getClassSeletion(stu).split(",");  
	    for(int i=0;i<course.length;i++) {
	    	ClassInfo cla=getOneClass(course[i]);
	    	labels[Integer.valueOf(course[i].charAt(6))-48+(Integer.valueOf(course[i].charAt(8))-48)/2*6].setText("<html>"+cla.getClassName()+"<br>"+cla.getTeacher()+"<br>"+cla.getClassroom()+"<html>");
	    	labels[Integer.valueOf(course[i].charAt(9))-48+(Integer.valueOf(course[i].charAt(11))-48)/2*6].setText("<html>"+cla.getClassName()+"<br>"+cla.getTeacher()+"<br>"+cla.getClassroom()+"<html>");
	    }
	}
	
	public void teacherSchedule(){
	    //TODO Student stu=App.session.getStudent();
	    List<ClassInfo> cla=getClassOfOneTeacher("任国林");
	    String[] course=new String[cla.size()*2];
	    for(int i=0;i<cla.size();i++) {
	    	course[i]=cla.get(i).getID();
	    }
	    for(int i=0;i<cla.size();i++) {
	    	System.out.println(course[i]);
	    	labels[Integer.valueOf(course[i].charAt(6))-48+(Integer.valueOf(course[i].charAt(8))-48)/2*6].setText("<html>"+cla.get(i).getClassName()+"<br>"+cla.get(i).getClassroom()+"<html>");
	    	labels[Integer.valueOf(course[i].charAt(9))-48+(Integer.valueOf(course[i].charAt(11))-48)/2*6].setText("<html>"+cla.get(i).getClassName()+"<br>"+cla.get(i).getClassroom()+"<html>");
	    }
	}
	/**
	 * Create the frame.
	 */
	public ScheduleTableGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel northFlowPanel = new JPanel();
        
        JPanel centerNullPanel = new JPanel(null);
        centerNullPanel.setBounds(0, 0, 800, 600);
        centerNullPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(centerNullPanel);
 
        Color darkerGray = Color.GRAY.darker();
        Color ligherBlack = darkerGray.darker().darker().darker();
        labels = new JLabel[36];
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                int index = i * 6 + j;
                labels[index] = new JLabel();
                labels[index].setBounds(j * 130, i * 100, 130, 100);
                labels[index].setOpaque(true);
                labels[index].setHorizontalAlignment(JTextField.CENTER);
                if ((i & 1) == 0)
                    labels[index].setBackground((j & 1) == 0 ? ligherBlack : darkerGray);
                else
                    labels[index].setBackground((j & 1) == 0 ? darkerGray : ligherBlack);
                labels[index].setForeground(Color.WHITE);
                centerNullPanel.add(labels[index]);
            }
        }
    
        // BLOCK ALERT: Should never be modified unless regulations changed
        labels[0].setText("<html><body><h1>课程表</h1></body></html>");
        ScheduleUtilities.setWeekLabels(labels);
        labels[6].setText("<html><body><h2>第1-2节<br /></h2>上午</body></html>");
        labels[12].setText("<html><body><h2>第3-4节<br /></h2>上午</body></html>");
        labels[18].setText("<html><body><h2>第5-6节<br /></h2>下午</body></html>");
        labels[24].setText("<html><body><h2>第7-8节<br /></h2>下午</body></html>");
        labels[30].setText("<html><body><h2>第9-10节<br /></h2>晚上</body></html>");
        
	}

	static class ScheduleUtilities
	{
	    private static ArrayList<ClassInfo> infos;
	    public static void setWeekLabels(JLabel[] labels)
	    {
	        labels[1].setText("<html><body><h2>星期一<br /></h2>" );
	        labels[2].setText("<html><body><h2>星期二<br /></h2>" );
	        labels[3].setText("<html><body><h2>星期三<br /></h2>" );
	        labels[4].setText("<html><body><h2>星期四<br /></h2>" );
	        labels[5].setText("<html><body><h2>星期五<br /></h2>" );
	    }
	}
}
