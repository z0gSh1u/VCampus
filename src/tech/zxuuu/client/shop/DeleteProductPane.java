package tech.zxuuu.client.shop;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.library.BookDetails;
import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Book;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DeleteProductPane extends JPanel {
	private JTextField txtName;
	private JTextField txtType;
	private JTable tblSearchList;
	private DefaultTableModel model;
	private List<Product> list = null;

	/**
	 * Create the panel.
	 */
	public DeleteProductPane() {
		this.setLayout(null);
		{
			JLabel lbl_ShopManage = new JLabel("商店管理中心·删除商品");
			lbl_ShopManage.setFont(new Font("幼圆", Font.BOLD, 30));
			lbl_ShopManage.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ShopManage.setBounds(14, 13, 815, 75);
			this.add(lbl_ShopManage);
		}
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(31, 187, 115, 33);
			this.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("幼圆", Font.BOLD, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(31, 245, 115, 33);
			this.add(lbl_Type);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(180, 187, 193, 41);
			this.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtType = new JTextField();
			txtType.setColumns(10);
			txtType.setBounds(180, 239, 193, 41);
			this.add(txtType);
		}
		{
			JButton btn_Confirm = new JButton("确认");
			btn_Confirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btn_Confirm.setFont(new Font("幼圆", Font.BOLD, 20));
			btn_Confirm.setBounds(610, 389, 167, 55);
			this.add(btn_Confirm);
		}


	

		String[] tableHeader= {"商品名称","类型","价格","库存量"};
		model=new DefaultTableModel(null, tableHeader);
		tblSearchList = new JTable();
		tblSearchList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					
					int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
					Product product = new Product();
					
					  product.setName(list.get(row).getName());
					  product.setType(list.get(row).getType());
					  product.setPrice(list.get(row).getPrice());
					  product.setNumber(list.get(row).getNumber());
					 
					 
					Boolean result = ResponseUtils
							.getResponseByHash((new Request(App.connectionToServer, null,
									"tech.zxuuu.server.shop.Addons.deleteProduct", new Object[] {product}).send()))
							.getReturn(Boolean.class);
					System.out.println(result);
					
					if (result) {
						SwingUtils.showMessage(null, "删除成功！", "提示");
					} 
					else {
						SwingUtils.showError(null, "删除失败！", "错误");

					}
					
				}}});
		tblSearchList.setBounds(2, 2, 300, 300);
		tblSearchList.setModel(model);
		JScrollPane jsp = new JScrollPane(tblSearchList);

		jsp.setBounds(398, 147, 394, 188);
		this.add(jsp);

		
		
		
		JLabel lbl_ProductList = new JLabel("商品列表·双击删除商品");
		lbl_ProductList.setFont(new Font("幼圆", Font.BOLD, 20));
		lbl_ProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductList.setBounds(395, 103, 397, 41);
		this.add(lbl_ProductList);

		JButton btn_Search = new JButton("搜索");
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.setName(txtName.getText());
				product.setType(txtType.getText());
				
				 list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.ProductServer.manageListProduct", new Object[] {product}).send())
						.getListReturn(Product.class);
				String[][] listData=new String[list.size()][4];
				model.setRowCount(0);
				if (list== null)
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中", "test");
				
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
				    tblSearchList.setModel(model);
					SwingUtils.showMessage(null,"Success", "test");
						
					}
				}
			
		});
		btn_Search.setFont(new Font("幼圆", Font.BOLD, 20));
		btn_Search.setBounds(83, 389, 167, 55);
		this.add(btn_Search);
		
		JLabel lbl_SearchProduct = new JLabel("搜索商品");
		lbl_SearchProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SearchProduct.setFont(new Font("幼圆", Font.BOLD, 20));
		lbl_SearchProduct.setBounds(31, 101, 342, 43);
		add(lbl_SearchProduct);
	}
}
