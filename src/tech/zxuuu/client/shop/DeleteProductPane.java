package tech.zxuuu.client.shop;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteProductPane extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DeleteProductPane() {
		this.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心·删除商品");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 665, 75);
			this.add(lbl_ShopManage);
		}
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(24, 149, 115, 33);
			this.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(24, 207, 115, 33);
			this.add(lbl_Type);
		}
		{
			JLabel lbl_Price = new JLabel("价格");
			lbl_Price.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Price.setBounds(24, 253, 115, 33);
			this.add(lbl_Price);
		}
		{
			textField = new JTextField();
			textField.setBounds(173, 149, 193, 41);
			this.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(173, 201, 193, 41);
			this.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(173, 253, 193, 41);
			this.add(textField_2);
		}
		{
			JButton btn_Confirm = new JButton("确认");
			btn_Confirm.setFont(new Font("幼圆", Font.BOLD, 20));
			btn_Confirm.setBounds(446, 389, 167, 55);
			this.add(btn_Confirm);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 147, 263, 188);
		this.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lbl_ProductList = new JLabel("商品列表");
		lbl_ProductList.setFont(new Font("幼圆", Font.BOLD, 20));
		lbl_ProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductList.setBounds(395, 103, 267, 41);
		this.add(lbl_ProductList);

		JButton btn_Search = new JButton("搜索");
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Search.setFont(new Font("幼圆", Font.BOLD, 20));
		btn_Search.setBounds(83, 389, 167, 55);
		this.add(btn_Search);
	}
}
