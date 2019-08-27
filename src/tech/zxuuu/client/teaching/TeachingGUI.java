package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeachingGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeachingGUI frame = new TeachingGUI();
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
	public TeachingGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pBody = new JPanel();
		contentPane.add(pBody, BorderLayout.CENTER);
		pBody.setLayout(null);
		
		JButton btnSelectClass = new JButton("选课");
		btnSelectClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassSelectGUI csg=new ClassSelectGUI();
				csg.setVisible(true);
			}
		});
		btnSelectClass.setBounds(78, 96, 93, 27);
		pBody.add(btnSelectClass);
		
		JButton btnSearchClass = new JButton("课表查询");
		btnSearchClass.setBounds(196, 96, 93, 27);
		pBody.add(btnSearchClass);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(319, 96, 93, 27);
		pBody.add(btnReturn);
		
		JLabel lblVcampus = new JLabel("VCampus教务管理系统");
		lblVcampus.setBounds(178, 38, 146, 18);
		pBody.add(lblVcampus);
		
		JLabel lblNewLabel = new JLabel("学生版");
		lblNewLabel.setBounds(445, 13, 53, 18);
		pBody.add(lblNewLabel);
	}
}
