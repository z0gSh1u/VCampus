package tech.zxuuu.client.shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopMessage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMessage frame = new ShopMessage();
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
	public ShopMessage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Message = new JLabel("网上商店·消息");
		lbl_Message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Message.setFont(new Font("幼圆", Font.BOLD, 30));
		lbl_Message.setBounds(0, 10, 763, 60);
		contentPane.add(lbl_Message);
		
		JButton btn_FIrstPage = new JButton("首页");
		btn_FIrstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopFirstPage shopFirstPage = new ShopFirstPage();
				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopFirstPage.setVisible(true);
			}
		});
		btn_FIrstPage.setBounds(88, 644, 92, 55);
		contentPane.add(btn_FIrstPage);
		
		JButton btn_Message = new JButton("消息");
		btn_Message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMessage shopMessage = new ShopMessage();
				shopMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMessage.setVisible(true);
			}
		});
		btn_Message.setBounds(221, 644, 92, 55);
		contentPane.add(btn_Message);
		
		JButton btn_Cart = new JButton("购物车");
		btn_Cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopCart shopCart = new ShopCart();
				shopCart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopCart.setVisible(true);
			}
		});
		btn_Cart.setBounds(349, 644, 92, 55);
		contentPane.add(btn_Cart);
		
		JButton btn_Mine = new JButton("我的");
		btn_Mine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMine shopMine = new ShopMine();
				shopMine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMine.setVisible(true);
			}
		});
		btn_Mine.setBounds(478, 644, 92, 55);
		contentPane.add(btn_Mine);
		
		JButton btn_Set = new JButton("设置");
		btn_Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopSet shopSet = new ShopSet();
				shopSet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopSet.setVisible(true);
			}
		});
		btn_Set.setBounds(603, 644, 92, 55);
		contentPane.add(btn_Set);
	}
}
