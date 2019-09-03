package tech.zxuuu.client.shop;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.client.main.App;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;

/**
 * 购物车面板
 * 
 * @author z0gSh1u
 */
public class CartPane extends JPanel {
	private JTable table;
	private DefaultTableModel model;
	String[] head = { "商品", "数量" };

	/**
	 * Create the panel.
	 */
	public void requireReRender() {
		model.setRowCount(0);
		for (Product product : ShopFirstPage.cart) {
			Object[] toAdd = { product.getName(), product.getNumber() };
			model.addRow(toAdd);
		}
	}

	public CartPane() {
		setBackground(new Color(135, 206, 250));
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		model = new DefaultTableModel(null, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};

		setLayout(null);

		JPanel that = this;

		JLabel lblNewLabel = new JLabel("购物车");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon(CartPane.class.getResource("/resources/assets/icon/cart2.png")));
		lblNewLabel.setBounds(14, 13, 106, 48);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				that.setVisible(false);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(CartPane.class.getResource("/resources/assets/icon/crossSign.png")));
		lblNewLabel_1.setBounds(324, 23, 32, 32);
		add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 74, 342, 344);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("校园卡结算");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checkoutHelper = "";
				for (Product ele : ShopFirstPage.cart) {
					checkoutHelper += ele.getName();
					checkoutHelper += "@";
					checkoutHelper += ele.getNumber();
					checkoutHelper += "@";
					checkoutHelper += ele.getPrice();
					checkoutHelper += "$";
				}
				// TODO: Can we transfer List<Entity> in Request?
				// Integer ret = ResponseUtils
				// .getResponseByHash(new Request(App.connectionToServer, null,
				// "tech.zxuuu.server.shop.checkout",
				// new Object[] { App.session.getStudent().getCardNumber(), ShopFirstPage.cart
				// }).send())
				// .getReturn(Integer.class);
				Integer ret = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.shop.Addons.checkout",
								new Object[] { App.session.getStudent().getCardNumber(), checkoutHelper }).send())
						.getReturn(Integer.class);
				if (ret.equals(-1)) {
					SwingUtils.showError(null, "非法请求！", "错误");
					ShopFirstPage.cart.clear();
					requireReRender();
					ShopFirstPage.lblCartCount.setText("0");
				} else if (ret.equals(0)) {
					SwingUtils.showMessage(null, "消费成功！", "提示");
					ShopFirstPage.cart.clear();
					requireReRender();
					ShopFirstPage.lblCartCount.setText("0");
				} else if (ret.equals(1)) {
					SwingUtils.showError(null, "一卡通余额不足！", "错误");
				} else if (ret.equals(2)) {
					SwingUtils.showError(null, "商品库存不足！", "错误");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(CartPane.class.getResource("/resources/assets/icon/checkout.png")));
		btnNewButton.setBounds(213, 431, 143, 41);
		add(btnNewButton);

		table.setModel(model);

	}
}
