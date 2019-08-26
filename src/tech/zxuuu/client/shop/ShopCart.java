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

public class ShopCart extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopCart frame = new ShopCart();
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
	public ShopCart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Message = new JLabel("网上商店·购物车");
		lbl_Message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Message.setFont(new Font("幼圆", Font.BOLD, 30));
		lbl_Message.setBounds(0, 10, 763, 60);
		contentPane.add(lbl_Message);
		
		JButton btn_FIrstPage = new JButton("首页");
		btn_FIrstPage.setBounds(88, 644, 92, 55);
		contentPane.add(btn_FIrstPage);
		
		JButton btn_Message = new JButton("消息");
		btn_Message.setBounds(221, 644, 92, 55);
		contentPane.add(btn_Message);
		
		JButton btn_Cart = new JButton("购物车");
		btn_Cart.setBounds(349, 644, 92, 55);
		contentPane.add(btn_Cart);
		
		JButton btn_Mine = new JButton("我的");
		btn_Mine.setBounds(478, 644, 92, 55);
		contentPane.add(btn_Mine);
		
		JButton btn_Set = new JButton("设置");
		btn_Set.setBounds(603, 644, 92, 55);
		contentPane.add(btn_Set);
	}
}
