package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;


	

	/**
	 * Launch the application.
	 */
	public class AffirmClassSelectionGUI extends JDialog {

		private JPanel contentPane;
		public JTextField txtClassID;
		public JTextField txtClassName;
		public JTextField txtTeacher;
		public JTextField txtTime;
		public JTextField txtClassroom;
		private final JPanel contentPanel = new JPanel();

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				AffirmClassSelectionGUI dialog = new AffirmClassSelectionGUI();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	/**
	 * Create the frame.
	 */
	public AffirmClassSelectionGUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 292);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(0, 0, 455, 187);
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
		
		txtTeacher = new JTextField();
		txtTeacher.setEditable(false);
		txtTeacher.setColumns(10);
		txtTeacher.setBounds(105, 78, 278, 24);
		panel.add(txtTeacher);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(105, 109, 278, 24);
		panel.add(txtTime);
		
		txtClassroom = new JTextField();
		txtClassroom.setEditable(false);
		txtClassroom.setColumns(10);
		txtClassroom.setBounds(105, 140, 278, 24);
		panel.add(txtClassroom);
		
		JButton btnConfirm = new JButton("确认选择");
		btnConfirm.setBounds(72, 205, 107, 27);
		contentPane.add(btnConfirm);
		
		JButton btnExit = new JButton("退出");
		btnExit.setBounds(261, 205, 113, 27);
		contentPane.add(btnExit);
	}
}
