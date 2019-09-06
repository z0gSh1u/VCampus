package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private JTextField txtISBN;

	/**
	 * Create the dialog.
	 */
	public MyBorrowGUI() {

		setModal(true);

		setTitle("我的借阅 - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyBorrowGUI.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setBounds(100, 100, 623, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		String[] tblhead = { "图书编号", "标题", "作者", "借阅时间" };
		model = new DefaultTableModel(null, tblhead) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};

		Long timeStamp = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));

		tblBeborrowed = new JTable();
		tblBeborrowed.setModel(model);
		tblBeborrowed.setBounds(2, 2, 300, 300);
		JScrollPane jsp = new JScrollPane(tblBeborrowed);
		jsp.setBounds(14, 47, 589, 182);
		tblBeborrowed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					txtISBN.setText(list.get(row).getISBN());
				}
			}
		});
		contentPanel.setLayout(null);
		contentPanel.add(jsp);

		JLabel lblNewLabel = new JLabel("我的借阅");
		lblNewLabel.setBounds(260, 16, 72, 18);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPanel.add(lblNewLabel);

		JButton btnReturn = new JButton("还书");
		btnReturn.setBounds(124, 314, 170, 80);
		btnReturn.setIcon(new ImageIcon(MyBorrowGUI.class.getResource("/resources/assets/icon/导入.png")));
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, App.session, "tech.zxuuu.server.library.BookServer.returnBook",
										new Object[] { App.session.getStudent().getCardNumber(), txtISBN.getText() }).send())
						.getReturn(Integer.class);
				if (result == 2) {
					SwingUtils.showMessage(null, "还书成功！", "提示");
				}
				if (result == 1) {
					SwingUtils.showError(null, "你不能归还本书！", "错误");
				}
				if (result == 0) {
					SwingUtils.showError(null, "无效的书目编码！", "错误");
				}
			}
		});
		contentPanel.add(btnReturn);

		JButton btnRenew = new JButton("续借");
		btnRenew.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnRenew.setIcon(new ImageIcon(MyBorrowGUI.class.getResource("/resources/assets/icon/导出.png")));
		btnRenew.setBounds(321, 314, 170, 80);
		btnReturn.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/导出.png")));
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, App.session,
								"tech.zxuuu.server.library.BookServer.renewBook", new Object[] { txtISBN.getText() }).send())
						.getReturn(Integer.class);
				if (result == 0)
					SwingUtils.showError(null, "续借失败，该书以逾期未还，请尽快归还", "提示");
				if (result == 1)
					SwingUtils.showError(null, "续借失败，已经续借一次", "提示");
				if (result == 2)
					SwingUtils.showMessage(null, "续借成功", "提示");
			}
		});
		contentPanel.add(btnRenew);

		txtISBN = new JTextField();
		txtISBN.setEditable(false);
		txtISBN.setBounds(170, 250, 186, 27);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblISBN = new JLabel("图书编号");
		lblISBN.setBounds(94, 254, 60, 18);
		contentPanel.add(lblISBN);

		JLabel lblNewLabel_1 = new JLabel("（双击图书，自动填写）");
		lblNewLabel_1.setBounds(368, 254, 165, 18);
		contentPanel.add(lblNewLabel_1);

		list = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Addons.getBorrowedBookList",
						new Object[] { App.session.getStudent().getCardNumber() }).send())
				.getListReturn(Book.class);

		if (list == null || list.size() == 0) {
			SwingUtils.showMessage(null, "暂无借阅书籍！", "提示");
			this.dispose();
		} else {
			model.setRowCount(0);
			int len = list.size();
			for (int i = 0; i < len; i++) {
				Object[] toAdd = { list.get(i).getISBN(), list.get(i).getTitle(), list.get(i).getAuthor(),
						list.get(i).getBorrowTime() };
				model.addRow(toAdd);
			}
		}

	}
}
