package tech.zxuuu.client.library;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Book;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
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
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// BookDetails dialog = new BookDetails();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public BookDetails(String title, String ISBN, String category, String details) {

		setBounds(100, 100, 700, 550);
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

		String[] head = { "isbn", "title", "author", "number" };
		model = new DefaultTableModel(null, head);
		// recommend
		tblRecommand = new JTable();
		tblRecommand.setModel(model);
		tblRecommand.setBounds(2, 2, 225, 105);
		JScrollPane jsp = new JScrollPane(tblRecommand);
		jsp.setBounds(14, 336, 624, 122);
		getContentPane().add(jsp);

		JButton btnRecommend = new JButton("相似图书推荐");
		btnRecommend.setIcon(new ImageIcon(BookDetails.class.getResource("/resources/assets/icon/zhishiku.png")));
		btnRecommend.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecommend.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnRecommend.setBounds(14, 286, 171, 41);
		btnRecommend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Request req = new Request(App.connectionToServer, null,
						"tech.zxuuu.server.library.BookServer.searchSimilarBook", new Object[] { title, txtCategory.getText() });
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);
				list = response.getListReturn(Book.class);
				String[][] listData = new String[list.size()][4];
				model.setRowCount(0);
				if (list == null) {
					SwingUtils.showMessage(null, "No finding", "test");
				} else {
					for (int i = 0; i < list.size(); i++) {
						listData[i][0] = list.get(i).getISBN();
						listData[i][1] = list.get(i).getTitle();
						listData[i][2] = list.get(i).getAuthor();
						// listData[i][3]=String.valueOf(list.get(i).getNumofborrowed());
						listData[i][3] = String.valueOf(list.get(i).getNumofborrowed());

					}

					model = new DefaultTableModel(listData, head) {
						@Override
						public boolean isCellEditable(int a, int b) {
							return false;
						}
					};
					tblRecommand.setModel(model);
					SwingUtils.showMessage(null, "Success", "test");
				}
			}
		});
		getContentPane().add(btnRecommend);

		txtCategory = new JTextField();
		txtCategory.setEditable(false);
		txtCategory.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtCategory.setBounds(140, 15, 119, 40);
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
		Request request = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.searchPicture",
				new Object[] { ISBN });
		String hash = request.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response response = ResponseQueue.getInstance().consume(hash);
		String result = response.getReturn(String.class);

		txtDetails = new JTextArea();
		txtDetails.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtDetails.setEditable(false);
		txtDetails.setBounds(140, 85, 264, 190);
		getContentPane().add(txtDetails);
		txtDetails.setLineWrap(true);
		txtDetails.setWrapStyleWord(true);
		this.txtDetails.setText(details);

		editPicture.setText("<html><body><img src=\"" + result + "\"></body></html>");

		System.out.println(editPicture.getText());

		this.txtCategory.setText(category);

		this.title = title;
		this.ISBN = ISBN;

	}
}
