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

import tech.zxuuu.client.library.DeleteBookPane;
import tech.zxuuu.client.library.NewBookPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * 图书管理员主界面
 * 
 * @author z0gSh1u
 */
public class AppLibraryManager extends JFrame {

	private JPanel contentPane;

	private JPanel currentDisplay;
	private JPanel newBookPane;
	private JPanel deleteBookPane;
	private JPanel defaultPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppLibraryManager frame = new AppLibraryManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppLibraryManager() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppLibraryManager.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("图书管理员 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/library.png")));
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("图书管理 - VCampus");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setBounds(102, 32, 239, 34);
		contentPane.add(lblVcampus);

		JLabel label_2 = new JLabel("当前登录卡号：");
		label_2.setBounds(355, 47, 105, 18);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(463, 47, 114, 18);
		contentPane.add(label_3);
		label_3.setText(App.session.getManager().getCardNumber());

		JButton btnNewButton = new JButton("书籍入库");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newBookPane.setVisible(true);
				currentDisplay = newBookPane;
			}
		});
		btnNewButton.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/jinru.png")));
		btnNewButton.setBounds(566, 20, 145, 57);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("书籍出库");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				deleteBookPane.setVisible(true);
				currentDisplay = deleteBookPane;
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/tuichu.png")));
		btnNewButton_1.setBounds(714, 20, 145, 57);
		contentPane.add(btnNewButton_1);

		defaultPane = new JPanel();
		defaultPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		defaultPane.setBounds(24, 102, 835, 498);
		contentPane.add(defaultPane);

		JLabel label_1 = new JLabel("选择一项以开始...");
		defaultPane.add(label_1);
		currentDisplay = defaultPane;

		newBookPane = new NewBookPane();
		newBookPane.setBounds(24, 102, 835, 498);
		newBookPane.setVisible(false);
		contentPane.add(newBookPane);
		deleteBookPane = new DeleteBookPane();
		deleteBookPane.setBounds(24, 102, 835, 498);
		deleteBookPane.setVisible(false);
		contentPane.add(deleteBookPane);

	}
}
