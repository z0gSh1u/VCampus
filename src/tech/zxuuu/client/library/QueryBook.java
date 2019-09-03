package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
import java.awt.event.InputEvent;

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
		getContentPane().setLayout(null);
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
        jsp.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.isMetaDown())
        		{
        			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
        			txtISBN.setText(list.get(row).getISBN());
        		}
        	}
        });
		
		JButton btnSearch = new JButton("检索");
		btnSearch.setBounds(0, 146, 63, 27);
		
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
		btnReset.setBounds(82, 146, 63, 27);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTitle.setText("");
				txtAuthor.setText("");
			}
		});
		contentPanel.add(btnReset);

		jsp.setBounds(159, 14, 359, 207);
		contentPanel.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(243, 300, 86, 24);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("所借书的条码");
		lblISBN.setBounds(112, 303, 90, 18);
		contentPanel.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(472, 299, 63, 27);
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
