package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Book;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;

/**
 * 我的借阅窗口
 * 
 * @author z0gSh1u
 */
public class MyBorrowGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblBeborrowed;
	private DefaultTableModel model;
	private List<Book> list = null;

	/**
	 * Create the dialog.
	 */
	public MyBorrowGUI() {

		setTitle("我的借阅 - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyBorrowGUI.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setBounds(100, 100, 623, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		String[] tblhead = { "图书编号", "标题", "作者" };
		model = new DefaultTableModel(null, tblhead) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		tblBeborrowed = new JTable();
		tblBeborrowed.setModel(model);
		tblBeborrowed.setBounds(2, 2, 300, 300);
		JScrollPane jsp = new JScrollPane(tblBeborrowed);
		jsp.setLocation(14, 47);
		jsp.setSize(589, 362);
		contentPanel.add(jsp);

		JLabel lblNewLabel = new JLabel("我的借阅");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(260, 16, 72, 18);
		contentPanel.add(lblNewLabel);

		list = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Addons.getBorrowedBookList",
						new Object[] { App.session.getStudent().getCardNumber() }).send())
				.getListReturn(Book.class);

		if (list == null || list.size() == 0) {
			SwingUtils.showMessage(null, "暂无借阅书籍！", "提示");
			return;
		} else {
			model.setRowCount(0);
			int len = list.size();
			for (int i = 0; i < len; i++) {
				Object[] toAdd = { list.get(i).getISBN(), list.get(i).getTitle(), list.get(i).getAuthor() };
				model.addRow(toAdd);
			}
		}

	}

}
