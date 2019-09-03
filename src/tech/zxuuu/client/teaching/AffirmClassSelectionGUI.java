package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


	

	/**
	 * Launch the application.
	 */
	public class AffirmClassSelectionGUI extends JDialog {

		private JPanel contentPane;
		public JTextField txtClassID;
		public JTextField txtClassName;
		public JTextField txtTime;
		public JTextField txtTeacher;
		public JTextField txtClassroom;
		private final JPanel contentPanel = new JPanel();
		JButton btnConfirm;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				AffirmClassSelectionGUI dialog = new AffirmClassSelectionGUI(new ClassSelectGUI(),0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public Boolean takeClass(Student student) {
			Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.takeClass",
					new Object[]{student}) ;
			String hash = req.send();
			ResponseUtils.blockAndWaitResponse(hash);
			Response resp =ResponseQueue.getInstance().consume(hash);
			return resp.getReturn(Boolean.class);
		}

		public Boolean judgeConflict() {
			//TODO String cn=App.session.getStudent().getClassNumber();
			String cn="0900011122340014203,0900022122560014203,";
			int num=cn.length()/20;
			String[] course =new String [num*2];
			for (int i=0;i<num;i++) {
				course[i*2]=cn.substring(i*20+6,i*20+9);
				course[i*2+1]=cn.substring(i*20+9,i*20+12);
			}
			for (int i=0;i<num*2;i++) {
				System.out.println(course[i]);
				System.out.println(txtClassID.getText().substring(6,9));
				if (txtClassID.getText().substring(6,9).equals(course[i])||txtClassID.getText().substring(9,12).contentEquals(course[i])) 
					return false;
			}
			return true;	
		}

	public AffirmClassSelectionGUI(ClassSelectGUI csg,int row) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 292);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(0, 0, 404, 187);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtClassID = new JTextField();
		txtClassID.setEditable(false);
		txtClassID.setBounds(105, 13, 278, 24);
		panel.add(txtClassID);
		txtClassID.setColumns(10);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(47, 16, 39, 18);
		panel.add(lblID);
		
		JLabel lblClassName = new JLabel("课程名");
		lblClassName.setBounds(47, 47, 57, 18);
		panel.add(lblClassName);
		
		JLabel lblTime = new JLabel("时间");
		lblTime.setBounds(47, 81, 57, 18);
		panel.add(lblTime);
		
		JLabel lblTeacher = new JLabel("教师");
		lblTeacher.setBounds(47, 112, 57, 18);
		panel.add(lblTeacher);
		
		JLabel lblClassroom = new JLabel("教室");
		lblClassroom.setBounds(47, 143, 57, 18);
		panel.add(lblClassroom);
		
		txtClassName = new JTextField();
		txtClassName.setEditable(false);
		txtClassName.setColumns(10);
		txtClassName.setBounds(105, 44, 278, 24);
		panel.add(txtClassName);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(105, 78, 278, 24);
		panel.add(txtTime);
		
		txtTeacher = new JTextField();
		txtTeacher.setEditable(false);
		txtTeacher.setColumns(10);
		txtTeacher.setBounds(105, 109, 278, 24);
		panel.add(txtTeacher);
		
		txtClassroom = new JTextField();
		txtClassroom.setEditable(false);
		txtClassroom.setColumns(10);
		txtClassroom.setBounds(105, 140, 278, 24);
		panel.add(txtClassroom);
		AffirmClassSelectionGUI acsg=this;
		
		btnConfirm = new JButton("确认选择");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Student stu=App.session.getStudent();
				stu.setClassNumber(txtClassID.getText()+","+stu.getClassNumber());
				takeClass(stu);
				csg.selectClass(row);
				acsg.dispose();
			}
		});
		
		btnConfirm.setBounds(72, 205, 107, 27);
		contentPane.add(btnConfirm);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acsg.dispose();
			}
		});
		btnReturn.setBounds(261, 205, 113, 27);
		contentPane.add(btnReturn);
	}
}
