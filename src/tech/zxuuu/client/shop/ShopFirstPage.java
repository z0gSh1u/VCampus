package tech.zxuuu.client.shop;

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.EventQueue;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTable;

public class ShopFirstPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Search;
	private DefaultTableModel model;
	public List<Product> cart;

	JPanel pnl_list;
	JScrollPane jsp_List;
	private JTable tab_Cart;

	public void handleTypeButtonClick(JButton btn) {
		pnl_list.removeAll();
		List<Product> list = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.shop.ProductServer.listProductByType", new Object[] { btn.getText() }).send())
				.getListReturn(Product.class);
		if (list == null)
			SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
		else {
			pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight() * list.size()));
			for (int i = 0; i < list.size(); i++) {
				JPanel paneli = new Blocks(list.get(i).getPicture(), list.get(i).getInformation(), list.get(i).getType(),
						list.get(i).getPrice());
				pnl_list.add(paneli);
			}

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		setResizable(false);
		setTitle("商店 - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopFirstPage.class.getResource("/resources/assets/icon/fav.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1138, 796);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		pnl_list = new JPanel();
		pnl_list.setLayout(new GridLayout(0, 1));

		jsp_List = new JScrollPane(pnl_list);
		jsp_List.setBounds(156, 171, 510, 531);

		panel.add(jsp_List);

		jsp_List.setViewportView(pnl_list);
		pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight() * 4));
		panel.revalidate(); // 告诉其他部件,我的宽高变了 this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt_Search = new JTextField();
		txt_Search.setBounds(40, 101, 526, 42);
		txt_Search.setText("可乐");
		txt_Search.setColumns(10);
		panel.add(txt_Search);

		JButton btn_Search = new JButton("搜索");
		btn_Search.setBounds(692, 101, 113, 42);

		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Search);
			}
		});

		btn_Search.setFont(new Font("幼圆", Font.BOLD, 15));
		panel.add(btn_Search);

		JButton btn_Food = new JButton("食物");
		btn_Food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Food);
			}
		});
		btn_Food.setBounds(62, 171, 69, 32);
		panel.add(btn_Food);

		JButton btn_Drink = new JButton("饮料");
		btn_Drink.setBounds(62, 229, 69, 32);
		btn_Drink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Drink);

			}
		});
		btn_Drink.setFont(new Font("幼圆", Font.PLAIN, 15));
		panel.add(btn_Drink);

		JButton btn_Fruit = new JButton("水果");
		btn_Fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Fruit);
			}
		});
		btn_Fruit.setBounds(62, 291, 69, 32);
		panel.add(btn_Fruit);

		JButton btn_Tool = new JButton("文具");
		btn_Tool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Tool);
			}
		});
		btn_Tool.setBounds(62, 355, 69, 32);
		panel.add(btn_Tool);

		JButton btn_Thing = new JButton("用品");
		btn_Thing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Thing);
			}
		});
		btn_Thing.setBounds(62, 413, 69, 32);
		btn_Thing.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(btn_Thing);

		btn_Search.setFont(new Font("幼圆", Font.BOLD, 15));
		btn_Search.setBounds(574, 101, 113, 42);
		panel.add(btn_Search);

		JButton btn_Cool = new JButton("冷饮");
		btn_Cool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Cool);
			}
		});
		btn_Cool.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_Cool.setBounds(62, 476, 69, 32);
		panel.add(btn_Cool);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 194, 477, 403);
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("购物车结算");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(885, 643, 107, 59);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/shop.png")));
		label.setBounds(14, 13, 64, 64);
		panel.add(label);
		
		JLabel lblVcampus = new JLabel("商店 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 239, 34);
		panel.add(lblVcampus);
		
		
		
		String[] tableHeader= {"商品名称","类型","价格","数量"};
		model=new DefaultTableModel(null, tableHeader);
		
		tab_Cart = new JTable();
		
		tab_Cart.setBounds(2, 2, 300, 300);
		tab_Cart.setModel(model);

		JScrollPane jsp_Cart = new JScrollPane(tab_Cart);
		jsp_Cart.setBounds(710, 171, 282, 426);
		panel.add(jsp_Cart);
		
		JButton btn_Refresh = new JButton("同步购物车");
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				 List<Product> list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.ProductServer.searchBuyer", new Object[] {App.session.getStudent().getCardNumber()}).send())
						.getListReturn(Product.class);
				String[][] listData=new String[list.size()][4];
				model.setRowCount(0);
				if (list== null)
					SwingUtils.showMessage(null, "抱歉，你还未添加商品到购物车，请添加", "test");
				
				else {
					for(int i = 0; i < list.size();i++)
					{
						listData[i][0]=list.get(i).getName();
						listData[i][1]=list.get(i).getType();
						listData[i][2]=String.valueOf(list.get(i).getPrice());
						listData[i][3]=String.valueOf(list.get(i).getNumber());
					}
					model= new DefaultTableModel(listData, tableHeader) {
						@Override
						public boolean isCellEditable(int a,int b) {
							return false;
						}
					};
				    tab_Cart.setModel(model);
					SwingUtils.showMessage(null,"Success", "test");
						
					}
			}
			
		});
		btn_Refresh.setBounds(707, 643, 107, 59);
		panel.add(btn_Refresh);
	}
}
