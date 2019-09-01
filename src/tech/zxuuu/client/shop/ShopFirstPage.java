package tech.zxuuu.client.shop;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.alibaba.fastjson.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class ShopFirstPage extends JFrame{

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
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
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
//
		
		
		  JPanel pnl_list = new JPanel(); pnl_list.setLayout(new GridLayout(0,1));
		  
		  JScrollPane jsp_List = new JScrollPane(pnl_list); jsp_List.setBounds(156,
		  171, 510, 421);
		  
		  panel.add(jsp_List);
		  
		  jsp_List.setViewportView(pnl_list); pnl_list.setPreferredSize(new
		  Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*4));
		  panel.revalidate(); //告诉其他部件,我的宽高变了 this.setVisible(true);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
//
		JLabel lbl_Shop = new JLabel("网上商店·首页");
		lbl_Shop.setBounds(40, 13, 763, 60);
		lbl_Shop.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Shop.setFont(new Font("幼圆", Font.BOLD, 30));
		panel.add(lbl_Shop);

		txt_Search = new JTextField();
		txt_Search.setBounds(40, 101, 510, 42);
		txt_Search.setText("可乐");
		txt_Search.setColumns(10);
		panel.add(txt_Search);

		JButton btn_Search = new JButton("搜索");
		btn_Search.setBounds(574, 101, 113, 42);
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.searchProduct",
						new Object[] {txt_Search.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);

//				SwingUtils.showMessage(null, list.get(0).getP_name(), "test");
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});



		/*Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.searchTypeByP_name",
						new Object[] {txt_Search.getText()});
				String hash = req.send();

				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				String type = response.getReturn(String.class);
	            if(type!=null)
				    SwingUtils.showMessage(null, "Success", "test");

			}*/

		btn_Search.setFont(new Font("幼圆", Font.BOLD, 15));
		panel.add(btn_Search);

		JButton btn_Food = new JButton("食物");
		btn_Food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Food.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});
		btn_Food.setBounds(62, 171, 69, 32);
		panel.add(btn_Food);

		JButton btn_Drink = new JButton("饮料");
		btn_Drink.setBounds(62, 229, 69, 32);
		btn_Drink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Drink.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}

			}
		});
		btn_Drink.setFont(new Font("幼圆", Font.PLAIN, 15));
		panel.add(btn_Drink);

		JButton btn_Fruit = new JButton("水果");
		btn_Fruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Fruit.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});
		btn_Fruit.setBounds(62, 291, 69, 32);
		panel.add(btn_Fruit);

		JButton btn_Tool = new JButton("文具");
		btn_Tool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Tool.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});
		btn_Tool.setBounds(62, 355, 69, 32);
		panel.add(btn_Tool);

		JButton btn_Thing = new JButton("用品");
		btn_Thing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Thing.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});
		btn_Thing.setBounds(62, 413, 69, 32);
		btn_Thing.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(btn_Thing);

		JButton btn_Set = new JButton("设置");
		btn_Set.setBounds(617, 657, 92, 55);
		btn_Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopSet shopSet = new ShopSet();
				shopSet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopSet.setVisible(true);
			}
		});
		panel.add(btn_Set);

		JButton btn_Mine = new JButton("我的");
		btn_Mine.setBounds(492, 657, 92, 55);
		btn_Mine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMine shopMine = new ShopMine();
				shopMine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMine.setVisible(true);
			}
		});
		panel.add(btn_Mine);

		JButton btn_Cart = new JButton("购物车");
		btn_Cart.setBounds(363, 657, 92, 55);
		btn_Cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShopCart shopCart = new ShopCart();
				shopCart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopCart.setVisible(true);
			}
		});
		panel.add(btn_Cart);

		JButton btn_Message = new JButton("消息");
		btn_Message.setBounds(235, 657, 92, 55);
		btn_Message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopMessage shopMessage = new ShopMessage();
				shopMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopMessage.setVisible(true);
			}
		});
		panel.add(btn_Message);

		JButton btn_FirstPage = new JButton("首页");
		btn_FirstPage.setBounds(102, 657, 92, 55);
		btn_FirstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopFirstPage shopFirstPage = new ShopFirstPage();
				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopFirstPage.setVisible(true);
			}
		});
		panel.add(btn_FirstPage);
		
		JButton btn_Cool = new JButton("冷饮");
		btn_Cool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.listProductByType",
						new Object[] {btn_Cool.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				else
				{
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new Blocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_list.add(paneli);
					}

				}
			}
		});
		btn_Cool.setFont(new Font("宋体", Font.PLAIN, 15));
		btn_Cool.setBounds(62, 476, 69, 32);
		panel.add(btn_Cool);


	}
}
