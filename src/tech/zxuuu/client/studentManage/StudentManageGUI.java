package tech.zxuuu.client.studentManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentManageGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManageGUI frame = new StudentManageGUI();
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
	public StudentManageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManager = new JLabel("学生管理系统");
		lblManager.setBounds(383, 5, 442, 29);
		contentPane.add(lblManager);
		
		JButton btnStudentIn = new JButton("学生入学");
		btnStudentIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InManager inManager = new InManager();
				inManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				inManager.setVisible(true);
			}
		});
		btnStudentIn.setBounds(42, 55, 388, 240);
		contentPane.add(btnStudentIn);
		
		JButton btnStudentOut = new JButton("学生退学");
		btnStudentOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OutManager outManager = new OutManager();
				outManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				outManager.setVisible(true);

			}
		});
		btnStudentOut.setBounds(42, 316, 388, 257);
		contentPane.add(btnStudentOut);
		
		JButton btnStudentTable = new JButton("学生列表");
		btnStudentTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentTableGUI studentTableGUI = new StudentTableGUI();
				studentTableGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				studentTableGUI.setVisible(true);
			}
		});
		btnStudentTable.setBounds(486, 55, 388, 240);
		contentPane.add(btnStudentTable);
		
		JButton btnSubjectSwitch = new JButton("转系管理");
		btnSubjectSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchManager switchManager = new SwitchManager();
				switchManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				switchManager.setVisible(true);
			}
		});
		btnSubjectSwitch.setBounds(486, 316, 388, 257);
		contentPane.add(btnSubjectSwitch);
	
		
	}
}
