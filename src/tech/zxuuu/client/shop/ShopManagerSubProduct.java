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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ShopManagerSubProduct extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

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
	public ShopManagerSubProduct() {
		setBounds(100, 100, 711, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心·删除商品");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 665, 75);
			contentPanel.add(lbl_ShopManage);
		}
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(24, 149, 115, 33);
			contentPanel.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(24, 207, 115, 33);
			contentPanel.add(lbl_Type);
		}
		{
			JLabel lbl_Price = new JLabel("价格");
			lbl_Price.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Price.setBounds(24, 253, 115, 33);
			contentPanel.add(lbl_Price);
		}
		{
			textField = new JTextField();
			textField.setBounds(173, 149, 193, 41);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(173, 201, 193, 41);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(173, 253, 193, 41);
			contentPanel.add(textField_2);
		}
		{
			JButton btn_Confirm = new JButton("确认");
			btn_Confirm.setFont(new Font("幼圆", Font.BOLD, 20));
			btn_Confirm.setBounds(446, 389, 167, 55);
			contentPanel.add(btn_Confirm);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 147, 263, 188);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lbl_ProductList = new JLabel("商品列表");
		lbl_ProductList.setFont(new Font("幼圆", Font.BOLD, 20));
		lbl_ProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductList.setBounds(395, 103, 267, 41);
		contentPanel.add(lbl_ProductList);
		
		JButton btn_Search = new JButton("搜索");
		btn_Search.setFont(new Font("幼圆", Font.BOLD, 20));
		btn_Search.setBounds(83, 389, 167, 55);
		contentPanel.add(btn_Search);
	}
}
