package tech.zxuuu.client.shop;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
//		
          JPanel pnl_CartList = new JPanel(); pnl_CartList.setLayout(new GridLayout(0,1));
		  
		  JScrollPane jsp_CartList = new JScrollPane(pnl_CartList); jsp_CartList.setBounds(39, 83, 531, 527);
		  
		  contentPane.add(jsp_CartList);
		  
		  jsp_CartList.setViewportView(pnl_CartList); pnl_CartList.setPreferredSize(new
		  Dimension(jsp_CartList.getWidth() - 50, jsp_CartList.getHeight()*4));
		  contentPane.revalidate(); //告诉其他部件,我的宽高变了 this.setVisible(true);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
		JLabel lbl_Message = new JLabel("网上商店·购物车");
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
		

		
		JButton btn_AllProduct = new JButton("所有商品");
		btn_AllProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.ProductServer.cartProduct",
						new Object[] {"213"});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				List<Product> list = response.getListReturn(Product.class);
				System.out.println("server result="+list);

//				SwingUtils.showMessage(null, list.get(0).getP_name(), "test");
				if(list==null)
					SwingUtils.showMessage(null, "抱歉，你还没有添加商品到购物车呢！", "test");
				else
				{
					pnl_CartList.setPreferredSize(new Dimension(jsp_CartList.getWidth() - 50, jsp_CartList.getHeight()*list.size()));
					for(int i = 0; i < list.size(); i++)
					{
						JPanel paneli = new CartBlocks(list.get(i).getPicture(),
								list.get(i).getInformation(),list.get(i).getType(),list.get(i).getPrice());
						pnl_CartList.add(paneli);
					}

				}
			}
		});
		btn_AllProduct.setBounds(603, 83, 128, 81);
		contentPane.add(btn_AllProduct);
	}
}
