package tech.zxuuu.client.shop;

import javax.swing.JPanel;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class CartBlocks extends JPanel {
	JTextField jtxt_Price;
	private JTextField jtxt_Type;

	/**
	 * Create the panel.
	 */
	public CartBlocks(String picture,String information,String type, float price) {
		setLayout(null);
		
		JButton btn_AddProduct = new JButton("移出购物车");
		btn_AddProduct.setFont(new Font("幼圆", Font.BOLD, 20));
		btn_AddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_AddProduct.setBounds(264, 238, 195, 49);
		add(btn_AddProduct);
		
		JEditorPane edp_Picture = new JEditorPane();
		edp_Picture.setEditable(false);
		edp_Picture.setContentType("text/html");
		edp_Picture.setBounds(14, 10, 236, 280);
		add(edp_Picture);
		edp_Picture.setText("<html><body>"+picture+"</body></html>");
   
		jtxt_Price = new JTextField();
		jtxt_Price.setFont(new Font("幼圆", Font.BOLD, 20));
		jtxt_Price.setHorizontalAlignment(SwingConstants.CENTER);
		jtxt_Price.setEditable(false);
		jtxt_Price.setBounds(339, 165, 120, 59);
		jtxt_Price.setText(jtxt_Price.getText()+price);
		add(jtxt_Price);
		jtxt_Price.setColumns(10);
		
		JTextArea txtA_Information = new JTextArea();
		txtA_Information.setFont(new Font("幼圆", Font.BOLD, 20));
		txtA_Information.setEditable(false);
		txtA_Information.setBounds(264, 10, 195, 95);
		txtA_Information.setText(information);
		add(txtA_Information);
		
		JLabel lbl_Price = new JLabel("价格￥");
		lbl_Price.setFont(new Font("幼圆", Font.PLAIN, 19));
		lbl_Price.setBounds(268, 166, 70, 59);
		add(lbl_Price);
		
		JLabel lbl_Type = new JLabel("类型");
		lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Type.setFont(new Font("幼圆", Font.PLAIN, 20));
		lbl_Type.setBounds(264, 105, 74, 59);
		add(lbl_Type);
		
		jtxt_Type = new JTextField();
		jtxt_Type.setFont(new Font("幼圆", Font.BOLD, 20));
		jtxt_Type.setHorizontalAlignment(SwingConstants.CENTER);
		jtxt_Type.setEditable(false);
		jtxt_Type.setBounds(339, 107, 120, 59);
		jtxt_Type.setText(type);
		add(jtxt_Type);
		jtxt_Type.setColumns(10);

	}
}
