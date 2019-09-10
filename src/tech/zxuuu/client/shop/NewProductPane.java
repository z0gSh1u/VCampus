package tech.zxuuu.client.shop;

import java.awt.Desktop;
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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class NewProductPane extends JPanel {
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtImage;
	private JTextField txtCount;
	private JTextField txtInformation;

	/**
	 * 商品新增面板
	 * 
	 * @author 杨鹏杰
	 * @modify z0gSh1u
	 */
	public NewProductPane() {
		this.setLayout(null);

		JLabel lbl_ShopManage = new JLabel("商店管理中心·添加商品");
		lbl_ShopManage.setFont(new Font("微软雅黑", Font.BOLD, 25));
		lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ShopManage.setBounds(14, 13, 592, 75);
		this.add(lbl_ShopManage);

		JLabel lbl_ProductName = new JLabel("商品名称");
		lbl_ProductName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductName.setBounds(38, 110, 115, 33);
		this.add(lbl_ProductName);

		JLabel lbl_Type = new JLabel("类型");
		lbl_Type.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Type.setBounds(38, 168, 115, 33);
		this.add(lbl_Type);

		JLabel lbl_Price = new JLabel("价格");
		lbl_Price.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Price.setBounds(38, 220, 115, 33);
		this.add(lbl_Price);

		JLabel lbl_Picture = new JLabel("图片");
		lbl_Picture.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Picture.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Picture.setBounds(38, 271, 115, 33);
		this.add(lbl_Picture);

		JLabel lbl_AddNumber = new JLabel("添加数量");
		lbl_AddNumber.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_AddNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AddNumber.setBounds(38, 325, 115, 33);
		this.add(lbl_AddNumber);

		txtName = new JTextField();
		txtName.setBounds(208, 108, 388, 41);
		this.add(txtName);
		txtName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(208, 212, 388, 41);
		this.add(txtPrice);

		txtImage = new JTextField();
		txtImage.setColumns(10);
		txtImage.setBounds(208, 263, 388, 41);
		this.add(txtImage);

		txtCount = new JTextField();
		txtCount.setColumns(10);
		txtCount.setBounds(208, 317, 388, 41);
		this.add(txtCount);

		JComboBox cbType = new JComboBox();

		JButton btn_Confirm = new JButton("确认");
		btn_Confirm.setIcon(new ImageIcon(NewProductPane.class.getResource("/resources/assets/icon/tick.png")));
		btn_Confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!SwingUtils.isPureDigits(txtCount.getText()) || !SwingUtils.isPureDigits(txtPrice.getText())) {
					SwingUtils.showError(null, "输入不合法，请检查！", "错误");
				}

				Product product = new Product();
				product.setName(txtName.getText());
				product.setNumber(Integer.parseInt(txtCount.getText()));
				product.setPicture("<img src=\"" + txtImage.getText() + "\" />");
				product.setType((String) cbType.getSelectedItem());
				product.setPrice(Float.parseFloat(txtPrice.getText()));
				product.setInformation(txtInformation.getText());

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
		btn_Confirm.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btn_Confirm.setBounds(428, 425, 167, 55);
		this.add(btn_Confirm);

		JLabel lbl_ProductInformation = new JLabel("详细信息");
		lbl_ProductInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductInformation.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_ProductInformation.setBounds(38, 378, 115, 33);
		add(lbl_ProductInformation);

		txtInformation = new JTextField();
		txtInformation.setColumns(10);
		txtInformation.setBounds(208, 371, 388, 41);
		add(txtInformation);

		cbType.setFont(new Font("宋体", Font.PLAIN, 16));
		cbType.setModel(new DefaultComboBoxModel(new String[] { "食物", "饮料", "水果", "文具", "用品" }));
		cbType.setBounds(208, 168, 388, 30);
		add(cbType);

		JButton btnLinkImg = new JButton("图片外链平台");
		btnLinkImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://zxuuu.tech:8080/vcout/form.html"));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "打开失败！", "错误");
				}
			}
		});
		btnLinkImg.setBounds(59, 441, 123, 27);
		add(btnLinkImg);
	}
}
