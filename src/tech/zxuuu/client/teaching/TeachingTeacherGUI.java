package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class TeachingTeacherGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeachingTeacherGUI frame = new TeachingTeacherGUI();
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
	public TeachingTeacherGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 512, 346);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVCampus = new JLabel("VCampus教务管理系统");
		lblVCampus.setBounds(188, 35, 146, 18);
		panel_1.add(lblVCampus);
		
		JLabel lblNewLabel = new JLabel("教师版");
		lblNewLabel.setBounds(440, 5, 72, 18);
		panel_1.add(lblNewLabel);
		
		JButton btnSearchClass = new JButton("教师课表查询");
		btnSearchClass.setBounds(94, 117, 132, 27);
		panel_1.add(btnSearchClass);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(281, 117, 126, 27);
		panel_1.add(btnReturn);
	}
}
