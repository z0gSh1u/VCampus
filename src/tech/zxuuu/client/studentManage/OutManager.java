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
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OutManager extends JFrame {

	private JPanel contentPane;
	private JTextField textCardNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutManager frame = new OutManager();
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
	public OutManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonYes = new JButton("确定");
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textCardNumber.getText().length()!=9) {SwingUtils.showMessage(null, "一卡通长度错误！！", "错误");}
				else 
				{
				String cardnumber = textCardNumber.getText();
				System.out.println(cardnumber);
				
				Request request = new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.deleteStudent", 
					new Object[] {cardnumber});
				String hash = request.send();
				
				
				System.out.println("req hash=" + hash);
				
				ResponseUtils.blockAndWaitResponse(hash);
				Response resp = ResponseQueue.getInstance().consume(hash);
				
				if (resp.getReturn(Boolean.class)) {
					SwingUtils.showMessage(null, "退学成功！", "hint");
				} else {
					SwingUtils.showMessage(null, "查无此人，退学失败！", "hint");
				}
				}
			}
		});
		buttonYes.setBounds(266, 402, 153, 37);
		contentPane.add(buttonYes);
		
		JLabel lblOutManage = new JLabel("学生退学管理");
		lblOutManage.setBounds(266, 0, 371, 98);
		contentPane.add(lblOutManage);
		
		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(116, 186, 125, 54);
		contentPane.add(lblCardNumber);
		
		textCardNumber = new JTextField();
		textCardNumber.setBounds(278, 196, 267, 37);
		contentPane.add(textCardNumber);
		textCardNumber.setColumns(10);
	}

}
