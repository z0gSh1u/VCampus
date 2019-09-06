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
import javax.swing.ImageIcon;
import java.awt.Font;

/**
 * 书籍搜索与借阅窗口
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class QueryBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblSearch;
	private DefaultTableModel model;
	private JTextField txtISBN;
	private List<Book> list = null;

	/**
	 * Create the dialog.
	 */
	public QueryBook() {
		setResizable(false);
		setTitle("书籍检索与借阅 - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(QueryBook.class.getResource("/resources/assets/icon/fav.png")));
		setBounds(100, 100, 808, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		txtTitle = new JTextField();
		txtTitle.setBounds(65, 15, 233, 30);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblTitle = new JLabel("书名");
		lblTitle.setBounds(21, 21, 30, 18);
		contentPanel.add(lblTitle);

		txtAuthor = new JTextField();
		txtAuthor.setBounds(356, 15, 157, 30);
		txtAuthor.setText("");
		contentPanel.add(txtAuthor);
		txtAuthor.setColumns(10);

		JLabel lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(312, 21, 30, 18);
		contentPanel.add(lblAuthor);

		String[] tableHeader = { "书目编号", "书名", "作者" };
		model = new DefaultTableModel(null, tableHeader);
		tblSearch = new JTable();
		tblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());

					BookDetails details = new BookDetails(list.get(row).getTitle(), list.get(row).getISBN(),
							list.get(row).getCategory(), list.get(row).getDetails());
					details.setModal(true);
					details.setVisible(true);
				}
				if (e.getClickCount() == 1) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					txtISBN.setText(list.get(row).getISBN());
				}
			}
		});
		JScrollPane jsp = new JScrollPane(tblSearch);

		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(546, 15, 100, 30);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.library.BookServer.fuzzySearchByTitleAndAuthor",
								new Object[] { txtTitle.getText().trim(), txtAuthor.getText().trim() }).send())
						.getListReturn(Book.class);
				String[][] listData = new String[list.size()][3];
				model.setRowCount(0);
				if (list == null) {
					SwingUtils.showMessage(null, "No finding", "test");
				} else {
					for (int i = 0; i < list.size(); i++) {
						listData[i][0] = list.get(i).getISBN();
						listData[i][1] = list.get(i).getTitle();
						listData[i][2] = list.get(i).getAuthor();
					}
					model = new DefaultTableModel(listData, tableHeader) {
						@Override
						public boolean isCellEditable(int a, int b) {
							return false;
						}
					};
					tblSearch.setModel(model);
					SwingUtils.showMessage(null, "查询完毕！", "提示");
				}
			}

		});

		contentPanel.add(btnSearch);
		JButton btnReset = new JButton("重置");
		btnReset.setBounds(661, 15, 100, 30);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTitle.setText("");
				txtAuthor.setText("");
				model.setRowCount(0);
			}
		});
		contentPanel.add(btnReset);

		jsp.setBounds(21, 66, 740, 266);
		contentPanel.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);

		txtISBN = new JTextField();
		txtISBN.setBounds(271, 379, 267, 30);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblISBN = new JLabel("需要借阅的书目编号");
		lblISBN.setBounds(122, 385, 135, 18);
		contentPanel.add(lblISBN);

		JButton btnComfirm = new JButton("确认");
		btnComfirm.setFont(new Font("宋体", Font.PLAIN, 18));
		btnComfirm.setIcon(new ImageIcon(QueryBook.class.getResource("/resources/assets/icon/tick.png")));
		btnComfirm.setBounds(552, 362, 121, 57);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, App.session, "tech.zxuuu.server.library.BookServer.borrowBook",
										new Object[] { App.session.getStudent().getCardNumber(), txtISBN.getText() }).send())
						.getReturn(Integer.class);
				if (result == 2) {
					SwingUtils.showMessage(null, "借阅成功！", "提示");
				}
				if (result == 1) {
					SwingUtils.showError(null, "本书已被借阅！", "错误");
				}
				if (result == 0) {
					SwingUtils.showError(null, "无效的书目编号！", "错误");
				}
			}
		});
		contentPanel.add(btnComfirm);

		JLabel lblNewLabel = new JLabel("双击表项查看书籍详情...");
		lblNewLabel.setBounds(21, 345, 174, 18);
		contentPanel.add(lblNewLabel);
	}
}
