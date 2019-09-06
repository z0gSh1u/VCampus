package tech.zxuuu.client.shop;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;

import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * 商店学生主页
 * 
 * @author 杨鹏杰
 * @modify z0gSh1u
 */
public class ShopFirstPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Search;
	private DefaultTableModel model;

	CartPane cartPanel;

	public static List<Product> cart;
	public static JLabel lblCartCount;

	JPanel pnl_list;
	JScrollPane jsp_List;
	JLabel lblDefaultHint;

	public void handleTypeButtonClick(JButton btn) {
		pnl_list.removeAll();
		List<Product> list = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.shop.ProductServer.listProductByType", new Object[] { btn.getText() }).send())
				.getListReturn(Product.class);
		if (list == null) {
			SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中...", "提示");
		} else {
			pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, 280 * list.size()));
			for (int i = 0; i < list.size(); i++) {
				JPanel paneli = new Blocks(list.get(i).getPicture(), list.get(i).getInformation(), list.get(i).getType(),
						list.get(i).getPrice());
				paneli.setName(list.get(i).getName());
				pnl_list.add(paneli);
			}
		}
		// 滚动到顶端
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jsp_List.getVerticalScrollBar().setValue(0);
				lblDefaultHint.setVisible(false);
				jsp_List.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopFirstPage() {

		cart = new ArrayList<Product>();

		setResizable(false);
		setTitle("商店 - VCampus");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ShopFirstPage.class.getResource("/resources/assets/icon/fav.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 796);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		cartPanel = new CartPane();
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setBounds(108, 173, 378, 490);
		cartPanel.setVisible(false);

		JLabel label = new JLabel("");
		label.setBounds(14, 13, 64, 64);
		label.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/shop.png")));
		panel.add(label);
		panel.add(cartPanel);
		
		lblDefaultHint = new JLabel("选择一项分类来开始...");
		lblDefaultHint.setBounds(284, 272, 159, 18);
		cartPanel.add(lblDefaultHint);
		lblDefaultHint.setVisible(true);

		pnl_list = new JPanel();
		pnl_list.setLayout(new GridLayout(0, 1));

		jsp_List = new JScrollPane(pnl_list);
		jsp_List.setBounds(190, 173, 510, 531);

		panel.add(jsp_List);
		
		jsp_List.setVisible(false);

		jsp_List.setViewportView(pnl_list);
		pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight() * 2));
		panel.revalidate(); // 告诉其他部件,我的宽高变了 this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt_Search = new JTextField();
		txt_Search.setBounds(31, 101, 542, 42);
		txt_Search.setText("可乐");
		txt_Search.setColumns(10);
		panel.add(txt_Search);

		JButton btn_Search = new JButton("搜索");
		btn_Search.setBounds(587, 101, 113, 42);

		btn_Search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnl_list.removeAll();
				List<Product> list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.ProductServer.searchProduct", new Object[] { txt_Search.getText() }).send())
						.getListReturn(Product.class);
				if (list == null) {
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中...", "提示");
				} else {
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, 280 * list.size()));
					for (int i = 0; i < list.size(); i++) {
						JPanel paneli = new Blocks(list.get(i).getPicture(), list.get(i).getInformation(), list.get(i).getType(),
								list.get(i).getPrice());
						paneli.setName(list.get(i).getName());
						pnl_list.add(paneli);
					}
				}

			}
		});

		btn_Search.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		panel.add(btn_Search);

		btn_Search.setFont(new Font("幼圆", Font.BOLD, 15));
		panel.add(btn_Search);

		JLabel lblVcampus = new JLabel("商店 - VCampus");
		lblVcampus.setBounds(102, 32, 239, 34);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		panel.add(lblVcampus);

		String[] tableHeader = { "商品名称", "类型", "价格", "数量" };
		model = new DefaultTableModel(null, tableHeader);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 157, 167, 407);
		panel_1.setBorder(
				new TitledBorder(null, "\u5546\u54C1\u5206\u7C7B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btn_Food = new JButton("食物");
		btn_Food.setBounds(24, 39, 117, 57);
		panel_1.add(btn_Food);
		btn_Food.setFont(new Font("宋体", Font.PLAIN, 16));
		btn_Food.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/餐饮.png")));

		JButton btn_Drink = new JButton("饮料");
		btn_Drink.setBounds(24, 109, 117, 57);
		panel_1.add(btn_Drink);
		btn_Drink.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/饮品 (1).png")));
		btn_Drink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Drink);
			}
		});
		btn_Drink.setFont(new Font("宋体", Font.PLAIN, 16));

		JButton btn_Fruit = new JButton("水果");
		btn_Fruit.setBounds(24, 178, 117, 57);
		panel_1.add(btn_Fruit);
		btn_Fruit.setFont(new Font("宋体", Font.PLAIN, 16));
		btn_Fruit.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/水果 (1).png")));

		JButton btn_Tool = new JButton("文具");
		btn_Tool.setBounds(24, 248, 117, 57);
		panel_1.add(btn_Tool);
		btn_Tool.setFont(new Font("宋体", Font.PLAIN, 16));
		btn_Tool.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/家具.png")));

		JButton btn_Thing = new JButton("用品");
		btn_Thing.setBounds(24, 318, 117, 57);
		panel_1.add(btn_Thing);
		btn_Thing.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/日用品.png")));
		btn_Thing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Thing);
			}
		});
		btn_Thing.setFont(new Font("宋体", Font.PLAIN, 16));

		lblCartCount = new JLabel("0");
		lblCartCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblCartCount.setBackground(Color.WHITE);
		lblCartCount.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblCartCount.setForeground(Color.RED);
		lblCartCount.setBounds(10, 562, 55, 40);
		panel.add(lblCartCount);

		JButton btnCart = new JButton("");
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO 自动生成的方法存根
						cartPanel.setVisible(false);
						cartPanel.requireReRender();
						cartPanel.setVisible(true);
					}
				});
				
			}
		});
		btnCart.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/cart.png")));
		btnCart.setBounds(15, 578, 161, 137);
		panel.add(btnCart);

		btn_Tool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Tool);
			}
		});
		btn_Fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Fruit);
			}
		});
		btn_Food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Food);
			}
		});

	}
}
