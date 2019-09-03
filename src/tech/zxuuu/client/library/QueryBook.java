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

public class QueryBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblSearch;
	private DefaultTableModel model;
	private JTextField txtISBN;
	private List<Book> list = null;

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
		setBounds(100, 100, 808, 501);
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
		txtTitle.setBounds(103, 15, 157, 30);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblTitle = new JLabel("标题");
		lblTitle.setBounds(15, 14, 89, 33);
		contentPanel.add(lblTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(367, 15, 157, 30);
		txtAuthor.setText("");
		contentPanel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(281, 20, 81, 21);
		contentPanel.add(lblAuthor);
		
		String[] tableHeader= {"isbn","title","author"};
		model=new DefaultTableModel(null, tableHeader);
		tblSearch = new JTable();
		tblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==2) {
					int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
		            BookDetails details=
		            		new BookDetails(
		            				list.get(row).getTitle(),
		            				list.get(row).getISBN(),
		            				list.get(row).getCategory(),
		            				list.get(row).getDetails());

		            details.setModal(true);
		            details.setVisible(true);
			}
		}});
        JScrollPane jsp = new JScrollPane(tblSearch);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(556, 15, 100, 30);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.fuzzySearchByTitleAndAuthor", 
					new Object[] {txtTitle.getText(),txtAuthor.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);

				list = response.getListReturn(Book.class);
				String[][] listData=new String[list.size()][3];
				model.setRowCount(0);
				if(list == null) {
					SwingUtils.showMessage(null, "No finding","test");
				}
				else {
					for(int i=0;i<list.size();i++) {
						listData[i][0]=list.get(i).getISBN();
						listData[i][1]=list.get(i).getTitle();
						listData[i][2]=list.get(i).getAuthor();
					}
					model= new DefaultTableModel(listData, tableHeader) {
						@Override
						public boolean isCellEditable(int a,int b) {
							   return false;
						}
					};
				    tblSearch.setModel(model);
					SwingUtils.showMessage(null,"Success", "test");

				}
			}
			
		});

		contentPanel.add(btnSearch);
		JButton btnReset = new JButton("重置");
		btnReset.setBounds(671, 15, 100, 30);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTitle.setText("");
				txtAuthor.setText("");
			}
		});
		contentPanel.add(btnReset);

		jsp.setBounds(21, 66, 740, 233);
		contentPanel.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(173, 332, 555, 24);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("图书编号");
		lblISBN.setBounds(22, 320, 130, 48);
		contentPanel.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(318, 379, 100, 30);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request request=new Request(App.connectionToServer,App.session,"tech.zxuuu.server.library.BookServer.borrowBook",
						new Object[] {txtISBN.getText()});
				String hash=request.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response=ResponseQueue.getInstance().consume(hash);

				int result=response.getReturn(Integer.class);
				System.out.println(result);
				if(result==2)
				  SwingUtils.showMessage(null, "Succeed borrowing", "test");
				if(result==1)
					SwingUtils.showError(null, "The book has been borrowed", "test");
				if(result==0)
					SwingUtils.showError(null, "The ISBN is invalid", "test");

			}
		});
		contentPanel.add(btnComfirm);
	}
}
