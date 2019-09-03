package tech.zxuuu.client.library;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Book;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * 书籍详情窗口
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class BookDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable tblRecommand;
	private DefaultTableModel model;
	public JTextField txtCategory;
	public String title;
	public String ISBN;
	private List<Book> list = null;

	JTextArea txtDetails = null;

	/**
	 * Create the dialog.
	 */
	public BookDetails(String title, String ISBN, String category, String details) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetails.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setBounds(100, 100, 680, 550);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblDetails = new JLabel("图书简介");
		lblDetails.setIcon(new ImageIcon(BookDetails.class.getResource("/resources/assets/icon/jianjie.png")));
		lblDetails.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblDetails.setBounds(14, 78, 104, 32);
		getContentPane().add(lblDetails);

		String[] head = { "藏书号", "书名", "作者", "被借次数" };
		model = new DefaultTableModel(null, head);

		tblRecommand = new JTable();
		tblRecommand.setModel(model);
		tblRecommand.setBounds(2, 2, 225, 105);
		JScrollPane jsp = new JScrollPane(tblRecommand);
		jsp.setBounds(14, 336, 624, 154);
		getContentPane().add(jsp);
		list = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
				"tech.zxuuu.server.library.BookServer.searchSimilarBook", new Object[] { title, txtCategory.getText() }).send())
				.getListReturn(Book.class);
		model.setRowCount(0);
		String[][] listData = null;
		if (list == null) {
			listData = new String[1][4];
			listData[0][0] = "未找到相关书籍...";
			listData[0][1] = listData[0][2] = listData[0][3] = "";
		} else {
			listData = new String[list.size()][4];
			for (int i = 0; i < list.size(); i++) {
				listData[i][0] = list.get(i).getISBN();
				listData[i][1] = list.get(i).getTitle();
				listData[i][2] = list.get(i).getAuthor();
				listData[i][3] = String.valueOf(list.get(i).getNumofborrowed());
			}
		}
		model = new DefaultTableModel(listData, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		tblRecommand.setModel(model);

		txtCategory = new JTextField();
		txtCategory.setEditable(false);
		txtCategory.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtCategory.setBounds(140, 22, 119, 40);
		getContentPane().add(txtCategory);
		txtCategory.setColumns(10);

		JLabel lblCategory = new JLabel("分类");
		lblCategory.setIcon(new ImageIcon(BookDetails.class.getResource("/resources/assets/icon/fenlei.png")));
		lblCategory.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblCategory.setBounds(14, 25, 70, 32);
		getContentPane().add(lblCategory);

		JEditorPane editPicture = new JEditorPane();
		editPicture.setEditable(false);
		editPicture.setBounds(453, 21, 185, 260);
		editPicture.setContentType("text/html");
		getContentPane().add(editPicture);

		String result = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.library.BookServer.searchPicture", new Object[] { ISBN }).send())
				.getReturn(String.class);

		txtDetails = new JTextArea();
		txtDetails.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtDetails.setEditable(false);
		txtDetails.setBounds(140, 85, 264, 190);
		getContentPane().add(txtDetails);
		txtDetails.setLineWrap(true);
		txtDetails.setWrapStyleWord(true);
		this.txtDetails.setText(details);

		editPicture.setText("<html><body><img src=\"" + result + "\"></body></html>");

		this.txtCategory.setText(category);

		JLabel label = new JLabel("相似图书推荐");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setIcon(new ImageIcon(BookDetails.class.getResource("/resources/assets/icon/zhishiku.png")));
		label.setBounds(248, 302, 144, 32);
		getContentPane().add(label);
		this.title = title;
		this.ISBN = ISBN;

	}
}
