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

public class QueryBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblSearch;
	private DefaultTableModel model;
	private JTextField txtISBN; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QueryBook dialog = new QueryBook();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QueryBook() {
		setBounds(100, 100, 591, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		contentPanel.setLayout(null);
		txtTitle = new JTextField();
		txtTitle.setBounds(59, 11, 86, 24);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblTitle = new JLabel("题名");
		lblTitle.setBounds(15, 14, 30, 18);
		contentPanel.add(lblTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(59, 68, 86, 24);
		txtAuthor.setText("");
		contentPanel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblAuthor = new JLabel("责任者");
		lblAuthor.setBounds(0, 71, 45, 18);
		contentPanel.add(lblAuthor);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(29, 156, 63, 27);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.fuzzySearchByTitleAndAuthor", 
					new Object[] {txtTitle.getText(),txtAuthor.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);
				List<Book> list = response.getListReturn(Book.class);
				model.setRowCount(0);
				if(list==null)
					SwingUtils.showMessage(null, "No finding","test");
				else {
				for (int i = 0; i < list.size(); i++) {
					Object[] toAdd = new Object[] {
							list.get(i).getISBN(), list.get(i).getTitle(), list.get(i).getAuthor()
					};
					model.addRow(toAdd);
				}
				SwingUtils.showMessage(null,"Success", "test");
				}
			}
			
		});
		contentPanel.add(btnSearch);
		
		JButton btnReset = new JButton("重置");
		btnReset.setBounds(125, 156, 63, 27);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPanel.add(btnReset);
		
		String[] book= {"isbn","title","author"};
		model=new DefaultTableModel(null,book);
		tblSearch = new JTable();
		JScrollPane jsp = new JScrollPane(tblSearch);
		jsp.setBounds(255, 142, 278, 135);
		contentPanel.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(125, 217, 86, 24);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("所借书的条码");
		lblISBN.setBounds(21, 220, 90, 18);
		contentPanel.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(125, 271, 63, 27);
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request request=new Request(App.connectionToServer,App.session,"tech.zxuuu.server.library.BookServer.borrowBook",
						new Object[] {txtISBN.getText()});
				String hash=request.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response=ResponseQueue.getInstance().consume(hash);
				Boolean result=response.getReturn(Boolean.class);
				if(result==true)
				  SwingUtils.showMessage(null, "Succeed borrowing", "test");
				
			}
		});
		contentPanel.add(btnComfirm);
	}
}
