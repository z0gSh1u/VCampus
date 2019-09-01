package tech.zxuuu.client.shop;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewProductPane extends JPanel {
	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtPrice;
	private JTextField txtImage;
	private JTextField txtCount;

	/**
	 * Create the panel.
	 */
	public NewProductPane() {
		this.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心·添加商品");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 665, 75);
			this.add(lbl_ShopManage);
		}
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(38, 110, 115, 33);
			this.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(38, 168, 115, 33);
			this.add(lbl_Type);
		}
		{
			JLabel lbl_Price = new JLabel("价格");
			lbl_Price.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Price.setBounds(38, 214, 115, 33);
			this.add(lbl_Price);
		}
		{
			JLabel lbl_Picture = new JLabel("图片");
			lbl_Picture.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Picture.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Picture.setBounds(38, 271, 115, 33);
			this.add(lbl_Picture);
		}
		{
			JLabel lbl_AddNumber = new JLabel("添加数量");
			lbl_AddNumber.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_AddNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_AddNumber.setBounds(38, 325, 115, 33);
			this.add(lbl_AddNumber);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(208, 108, 388, 41);
			this.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtType = new JTextField();
			txtType.setColumns(10);
			txtType.setBounds(208, 160, 388, 41);
			this.add(txtType);
		}
		{
			txtPrice = new JTextField();
			txtPrice.setColumns(10);
			txtPrice.setBounds(208, 212, 388, 41);
			this.add(txtPrice);
		}
		{
			txtImage = new JTextField();
			txtImage.setColumns(10);
			txtImage.setBounds(208, 263, 388, 41);
			this.add(txtImage);
		}
		{
			txtCount = new JTextField();
			txtCount.setColumns(10);
			txtCount.setBounds(208, 317, 388, 41);
			this.add(txtCount);
		}
		{
			JButton btn_Confirm = new JButton("确认");
			btn_Confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					Product product = new Product();
					product.setP_name(txtName.getText());
					product.setInformation(txtName.getText());
					product.setNumber(Integer.parseInt(txtCount.getText()));
					product.setPicture("<img src=\"" + txtImage.getText() + "\" />");
					product.setType(txtType.getText());
					product.setPrice(Float.parseFloat(txtPrice.getText()));

					Boolean result = ResponseUtils
							.getResponseByHash((new Request(App.connectionToServer, null,
									"tech.zxuuu.server.shop.Addons.insertNewProduct", new Object[] { product }).send()))
							.getReturn(Boolean.class);

					if (result) {
						SwingUtils.showMessage(null, "入库成功！", "提示");
					} else {
						SwingUtils.showError(null, "入库失败！", "错误");
					}

				}
			});
			btn_Confirm.setFont(new Font("幼圆", Font.BOLD, 20));
			btn_Confirm.setBounds(428, 390, 167, 55);
			this.add(btn_Confirm);
		}
	}

}
