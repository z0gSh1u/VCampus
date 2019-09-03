package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DropCourseGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField txtClassID;
	JTextField txtClassName;
	JTextField txtTime;
	JTextField txtTeacher;
	JTextField txtClassroom;
	
	public Boolean takeClass(Student student) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.takeClass",
				new Object[]{student}) ;
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp =ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(Boolean.class);
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			DropCourseGUI dialog = new DropCourseGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public DropCourseGUI(ClassSelectGUI csg,int row) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 216);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lbClassID = new JLabel("ID");
		lbClassID.setBounds(49, 32, 39, 18);
		contentPanel.add(lbClassID);
		
		txtClassID = new JTextField();
		txtClassID.setEditable(false);
		txtClassID.setColumns(10);
		txtClassID.setBounds(107, 29, 278, 24);
		contentPanel.add(txtClassID);
		
		JLabel lbClassName = new JLabel("课程名");
		lbClassName.setBounds(49, 63, 57, 18);
		contentPanel.add(lbClassName);
		
		txtClassName = new JTextField();
		txtClassName.setEditable(false);
		txtClassName.setColumns(10);
		txtClassName.setBounds(107, 60, 278, 24);
		contentPanel.add(txtClassName);
		
		JLabel lbTime = new JLabel("时间");
		lbTime.setBounds(49, 97, 57, 18);
		contentPanel.add(lbTime);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(107, 94, 278, 24);
		contentPanel.add(txtTime);
		
		JLabel lbTeacher = new JLabel("教师");
		lbTeacher.setBounds(49, 128, 57, 18);
		contentPanel.add(lbTeacher);
		
		txtTeacher = new JTextField();
		txtTeacher.setEditable(false);
		txtTeacher.setColumns(10);
		txtTeacher.setBounds(107, 125, 278, 24);
		contentPanel.add(txtTeacher);
		
		JLabel lbClassroom = new JLabel("教室");
		lbClassroom.setBounds(49, 159, 57, 18);
		contentPanel.add(lbClassroom);
		
		txtClassroom = new JTextField();
		txtClassroom.setEditable(false);
		txtClassroom.setColumns(10);
		txtClassroom.setBounds(107, 156, 278, 24);
		DropCourseGUI dcg=this;
		contentPanel.add(txtClassroom);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 216, 432, 37);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDrop = new JButton("退选课程");
				btnDrop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Student stu=App.session.getStudent();
						stu.setClassNumber(stu.getClassNumber().replace(txtClassID.getText()+",", ""));
						takeClass(stu);
						csg.dropCourse(row);
						dcg.dispose();
					}
				});
				btnDrop.setBounds(64, 5, 93, 27);
				btnDrop.setActionCommand("OK");
				buttonPane.add(btnDrop);
				getRootPane().setDefaultButton(btnDrop);
			}
			{
				JButton btnCancel = new JButton("返回");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dcg.dispose();
					}
				});
				btnCancel.setBounds(282, 5, 93, 27);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
