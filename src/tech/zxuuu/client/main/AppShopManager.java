package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import tech.zxuuu.client.shop.DeleteProductPane;
import tech.zxuuu.client.shop.NewProductPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AppShopManager extends JFrame {

	private JPanel btnSwitchToOut;

	private JPanel defaultPane, currentPane, newProductPane, deleteProductPane;

	/**
	 * Create the frame.
	 */
	public AppShopManager() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppShopManager.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("商店管理 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 665);
		btnSwitchToOut = new JPanel();
		btnSwitchToOut.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(btnSwitchToOut);
		btnSwitchToOut.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppShopManager.class.getResource("/resources/assets/icon/shop.png")));
		label.setBounds(14, 13, 64, 64);
		btnSwitchToOut.add(label);

		JLabel lblVcampus = new JLabel("商店管理 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 264, 34);
		btnSwitchToOut.add(lblVcampus);

		JLabel label_2 = new JLabel("当前登录卡号：");
		label_2.setBounds(354, 48, 105, 18);
		btnSwitchToOut.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(462, 48, 114, 18);
		btnSwitchToOut.add(label_3);
		label_3.setText(App.session.getManager().getCardNumber());

		JButton btnSwitchToIn = new JButton("商品入库");
		btnSwitchToIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPane.setVisible(false);
				newProductPane.setVisible(true);
				currentPane = newProductPane;
			}
		});
		btnSwitchToIn.setIcon(new ImageIcon(AppShopManager.class.getResource("/resources/assets/icon/jinru.png")));
		btnSwitchToIn.setBounds(564, 26, 145, 57);
		btnSwitchToOut.add(btnSwitchToIn);

		JButton btnNewButton_1 = new JButton("商品出库");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPane.setVisible(false);
				deleteProductPane.setVisible(true);
				currentPane = deleteProductPane;
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AppShopManager.class.getResource("/resources/assets/icon/tuichu.png")));
		btnNewButton_1.setBounds(722, 26, 145, 57);
		btnSwitchToOut.add(btnNewButton_1);

		defaultPane = new JPanel();
		defaultPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		defaultPane.setBounds(24, 90, 848, 527);
		btnSwitchToOut.add(defaultPane);

		JLabel label_1 = new JLabel("选择一个选项以开始...");
		defaultPane.add(label_1);

		currentPane = defaultPane;

		newProductPane = new NewProductPane();
		newProductPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		newProductPane.setBounds(24, 90, 848, 527);
		newProductPane.setVisible(false);
		btnSwitchToOut.add(newProductPane);

		deleteProductPane = new DeleteProductPane();
		deleteProductPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		deleteProductPane.setBounds(24, 90, 848, 527);
		deleteProductPane.setVisible(false);
		btnSwitchToOut.add(deleteProductPane);

	}

}
