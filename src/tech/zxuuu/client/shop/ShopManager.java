package tech.zxuuu.client.shop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopManager extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShopManager dialog = new ShopManager();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShopManager() {
		setBounds(100, 100, 711, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 665, 75);
			contentPanel.add(lbl_ShopManage);
		}
		{
			JButton btn_AddProduct = new JButton("添加商品");
			btn_AddProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ShopManagerAddProduct shopManagerAddProduct = new ShopManagerAddProduct();
					shopManagerAddProduct.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					shopManagerAddProduct.setVisible(true);
					
				}
			});
			btn_AddProduct.setFont(new Font("幼圆", Font.BOLD, 30));
			btn_AddProduct.setBounds(24, 101, 297, 352);
			contentPanel.add(btn_AddProduct);
		}
		{
			JButton btn_SubProduct = new JButton("删除商品");
			btn_SubProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ShopManagerSubProduct shopManagerSubProduct = new ShopManagerSubProduct();
					shopManagerSubProduct.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					shopManagerSubProduct.setVisible(true);
				}
			});
			btn_SubProduct.setFont(new Font("幼圆", Font.BOLD, 30));
			btn_SubProduct.setBounds(329, 104, 297, 349);
			contentPanel.add(btn_SubProduct);
		}
	}

}
