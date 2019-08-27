package tech.zxuuu.client.studentManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;

public class SwitchManager extends JFrame {

	private JPanel contentPane;
	private JTextField textCardNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwitchManager frame = new SwitchManager();
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
	public SwitchManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSwitch = new JLabel("学生转系管理");
		lblSwitch.setBounds(265, 0, 443, 66);
		contentPane.add(lblSwitch);
		
		textCardNumber = new JTextField();
		textCardNumber.setBounds(247, 155, 304, 35);
		contentPane.add(textCardNumber);
		textCardNumber.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(80, 141, 161, 57);
		contentPane.add(lblCardNumber);
		
		JLabel lblSubjectNumber = new JLabel("转系系号");
		lblSubjectNumber.setBounds(79, 200, 139, 64);
		contentPane.add(lblSubjectNumber);
		
		JComboBox comboSubjectNumber = new JComboBox();
		comboSubjectNumber.setBounds(247, 220, 304, 35);
		contentPane.add(comboSubjectNumber);
		
		JButton buttonYes = new JButton("确定");
		buttonYes.setBounds(264, 353, 153, 37);
		contentPane.add(buttonYes);
		
		
		
		comboSubjectNumber.addItem("01");
		comboSubjectNumber.addItem("02");
		comboSubjectNumber.addItem("03");
		comboSubjectNumber.addItem("04");
		comboSubjectNumber.addItem("05");
		comboSubjectNumber.addItem("06");
		comboSubjectNumber.addItem("07");
		comboSubjectNumber.addItem("08");
		comboSubjectNumber.addItem("09");
		comboSubjectNumber.addItem("10");
		comboSubjectNumber.addItem("11");
		comboSubjectNumber.addItem("12");
		comboSubjectNumber.addItem("13");
		comboSubjectNumber.addItem("14");
		comboSubjectNumber.addItem("15");
		comboSubjectNumber.addItem("16");
		comboSubjectNumber.addItem("17");
		comboSubjectNumber.addItem("19");
		comboSubjectNumber.addItem("21");
		comboSubjectNumber.addItem("22");
		comboSubjectNumber.addItem("24");
		comboSubjectNumber.addItem("25");
		comboSubjectNumber.addItem("41");
		comboSubjectNumber.addItem("42");
		comboSubjectNumber.addItem("43");
		comboSubjectNumber.addItem("57");
		comboSubjectNumber.addItem("61");
		comboSubjectNumber.addItem("71");
		comboSubjectNumber.addItem("88");

	}
}
