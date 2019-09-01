package tech.zxuuu.client.studentManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.sound.sampled.LineListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class SwitchManager extends JFrame {

	private JPanel contentPane;
	private JTextField textCardNumber;
	private JTextField textNewStudentNumber;

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
		textCardNumber.setBounds(247, 113, 304, 35);
		contentPane.add(textCardNumber);
		textCardNumber.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(79, 102, 161, 57);
		contentPane.add(lblCardNumber);
		
		JLabel lblSubjectNumber = new JLabel("转系系号");
		lblSubjectNumber.setBounds(79, 155, 139, 64);
		contentPane.add(lblSubjectNumber);
		
		JComboBox comboSubjectNumber = new JComboBox();
		comboSubjectNumber.setBounds(247, 170, 304, 35);
		contentPane.add(comboSubjectNumber);
		
		JButton buttonYes = new JButton("确定");
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textCardNumber.getText().length()!=9) {SwingUtils.showMessage(null, "一卡通长度错误！！", "错误");}
				else if(!(textNewStudentNumber.getText().substring(0, 2).equals(((String)comboSubjectNumber.getSelectedItem()).substring(0, 2)))) {
					SwingUtils.showMessage(null, "学号与系号不匹配！", "错误");
				}
				else if(textNewStudentNumber.getText().length()!=8) {SwingUtils.showMessage(null, "学号长度错误！", "错误");}
				else {
						Student student = new Student(textCardNumber.getText(), null);
						student.setStudentNumber(textNewStudentNumber.getText());
						student.setAcademy(textNewStudentNumber.getText().substring(0, 2));
						Request request = new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.switchStudent", 
								new Object[] {student.getCardNumber(),student.getAcademy(),student.getStudentNumber()});
						String hash = request.send();			
						ResponseUtils.blockAndWaitResponse(hash);
						Response resp = ResponseQueue.getInstance().consume(hash);
						String result = resp.getReturn(String.class);
						
						System.out.println("result="+result);
						
						if (result.equals("Nocard")) {
							SwingUtils.showMessage(null, "无此学生！", "错误");
						}
						else if(result.equals("Repeat")) {
							SwingUtils.showMessage(null, "此学号已有人使用！", "错误");
						}
						else if(result.equals("Ok")) {
							SwingUtils.showMessage(null, "转系成功！", "hint");
						}
						else {SwingUtils.showMessage(null, "系统错误！", "错误");}
				}
				
			}
		});
		buttonYes.setBounds(264, 353, 153, 37);
		contentPane.add(buttonYes);
		
		
		List<String> academies = new ArrayList<>(
			Arrays.asList(
				"01","02","03","04","05","06","07","08","09","10","11","12",
				"13","14","15","16","17","19","21","22","24","25","41","42","43","57","61","71","88")
			);
		for (String academy : academies) {
			comboSubjectNumber.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}

		
		
		JLabel lblNewStudentNumber = new JLabel("新学号");
		lblNewStudentNumber.setBounds(79, 230, 99, 25);
		contentPane.add(lblNewStudentNumber);
		
		textNewStudentNumber = new JTextField();
		textNewStudentNumber.setBounds(247, 227, 304, 31);
		contentPane.add(textNewStudentNumber);
		textNewStudentNumber.setColumns(10);

	}
}
