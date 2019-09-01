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

public class ShopManagerAddProduct extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
	public ShopManagerAddProduct() {
		setBounds(100, 100, 711, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心·添加商品");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 665, 75);
			contentPanel.add(lbl_ShopManage);
		}
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(38, 110, 115, 33);
			contentPanel.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(38, 168, 115, 33);
			contentPanel.add(lbl_Type);
		}
		{
			JLabel lbl_Price = new JLabel("价格");
			lbl_Price.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Price.setBounds(38, 214, 115, 33);
			contentPanel.add(lbl_Price);
		}
		{
			JLabel lbl_Picture = new JLabel("图片");
			lbl_Picture.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Picture.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Picture.setBounds(38, 271, 115, 33);
			contentPanel.add(lbl_Picture);
		}
		{
			JLabel lbl_AddNumber = new JLabel("添加数量");
			lbl_AddNumber.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_AddNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_AddNumber.setBounds(38, 325, 115, 33);
			contentPanel.add(lbl_AddNumber);
		}
		{
			textField = new JTextField();
			textField.setBounds(208, 108, 388, 41);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(208, 160, 388, 41);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(208, 212, 388, 41);
			contentPanel.add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(208, 263, 388, 41);
			contentPanel.add(textField_3);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(208, 317, 388, 41);
			contentPanel.add(textField_4);
		}
		{
			JButton btn_Confirm = new JButton("确认");
			btn_Confirm.setFont(new Font("幼圆", Font.BOLD, 20));
			btn_Confirm.setBounds(428, 390, 167, 55);
			contentPanel.add(btn_Confirm);
		}
	}

}
