package tech.zxuuu.client.shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ShopFirstPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopFirstPage frame = new ShopFirstPage();
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
	public ShopFirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 796);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lbl_Shop = new JLabel("网上商店·首页");
		lbl_Shop.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Shop.setFont(new Font("幼圆", Font.BOLD, 30));
		lbl_Shop.setBounds(14, 13, 763, 60);
		panel.add(lbl_Shop);
		
		txt_Search = new JTextField();
		txt_Search.setText("搜搜");
		txt_Search.setColumns(10);
		txt_Search.setBounds(40, 102, 510, 42);
		panel.add(txt_Search);
		
		JButton btn_Search = new JButton("搜索");
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.searchProduct",
						new Object[] {txt_Search.getText()});
				String hash = req.send();
				Response response = ResponseQueue.getInstance().consume(hash);
				
				List<Product> lst = response.getListReturn(Product.class);
				
				SwingUtils.showMessage(null, lst.get(0).getProduct(), "hint");
				
			}
		});
		btn_Search.setFont(new Font("幼圆", Font.BOLD, 15));
		btn_Search.setBounds(574, 101, 113, 42);
		panel.add(btn_Search);
		
		JButton btn_Recommend = new JButton("推荐");
		btn_Recommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Recommend.setBounds(62, 194, 69, 32);
		panel.add(btn_Recommend);
		
		JButton btn_Food = new JButton("食物");
		btn_Food.setBounds(62, 236, 69, 32);
		panel.add(btn_Food);
		
		JButton btn_Drink = new JButton("饮料");
		btn_Drink.setFont(new Font("幼圆", Font.PLAIN, 15));
		btn_Drink.setBounds(62, 274, 69, 32);
		panel.add(btn_Drink);
		
		JButton btn_Fruit = new JButton("水果");
		btn_Fruit.setBounds(62, 316, 69, 32);
		panel.add(btn_Fruit);
		
		JButton btn_Tool = new JButton("文具");
		btn_Tool.setBounds(62, 358, 69, 32);
		panel.add(btn_Tool);
		
		JButton btn_Thing = new JButton("用品");
		btn_Thing.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_Thing.setBounds(62, 400, 69, 32);
		panel.add(btn_Thing);
		
		JButton btn_Set = new JButton("设置");
		btn_Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopSet shopSet = new ShopSet();
				shopSet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopSet.setVisible(true);
			}
		});
		btn_Set.setBounds(617, 657, 92, 55);
		panel.add(btn_Set);
		
		JButton btn_Mine = new JButton("我的");
		btn_Mine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMine shopMine = new ShopMine();
				shopMine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMine.setVisible(true);
			}
		});
		btn_Mine.setBounds(492, 657, 92, 55);
		panel.add(btn_Mine);
		
		JButton btn_Cart = new JButton("购物车");
		btn_Cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShopCart shopCart = new ShopCart();
				shopCart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopCart.setVisible(true);
			}
		});
		btn_Cart.setBounds(363, 657, 92, 55);
		panel.add(btn_Cart);
		
		JButton btn_Message = new JButton("消息");
		btn_Message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMessage shopMessage = new ShopMessage();
				shopMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMessage.setVisible(true);
			}
		});
		btn_Message.setBounds(235, 657, 92, 55);
		panel.add(btn_Message);
		
		JButton btn_FirstPage = new JButton("首页");
		btn_FirstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopFirstPage shopFirstPage = new ShopFirstPage();
				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopFirstPage.setVisible(true);
			}
		});
		btn_FirstPage.setBounds(102, 657, 92, 55);
		panel.add(btn_FirstPage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 194, 477, 403);
		panel.add(scrollPane);
	}
}
