package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ReturnBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JButton btnCheck;
	private JTable tblBorrowed;
	private DefaultTableModel model;
	private List<Book> list=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReturnBook dialog = new ReturnBook();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReturnBook() {
		setBounds(100, 100, 530, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		txtISBN = new JTextField();
		txtISBN.setBounds(128, 266, 86, 24);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(285, 265, 113, 27);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request request=new Request(App.connectionToServer,App.session,"tech.zxuuu.server.library.BookServer.returnBook",
						new Object[] {txtISBN.getText()});
				String hash=request.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response=ResponseQueue.getInstance().consume(hash);

				int result=response.getReturn(Integer.class);
				if(result==2)
				   SwingUtils.showMessage(null, "Succeed returnning", "test");
				if(result==1)
				   SwingUtils.showError(null,"This book has not been borrowed", "test");
				if(result==0)
					SwingUtils.showError(null, "The ISBN is invalid", "test");
				}


		});
		contentPanel.add(btnComfirm);
		
		String[] tblhead= {"isbn","title","author"};
		btnCheck = new JButton("查看所借书");
		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.searchBeBorrowed", 
						new Object[] {App.session.getStudent().getCardNumber()});
					String hash = req.send();
					ResponseUtils.blockAndWaitResponse(hash);
					Response response = ResponseQueue.getInstance().consume(hash); 
					list =response.getListReturn(Book.class);
					String[][] listData=new String[list.size()][3];
					model.setRowCount(0);
					// 填充新数据
					if(list == null) {
						SwingUtils.showMessage(null, "No finding","test");
					}
					else {
						for(int i=0;i<list.size();i++) {
							listData[i][0]=list.get(i).getISBN();
							listData[i][1]=list.get(i).getTitle();
							listData[i][2]=list.get(i).getAuthor();
						}
						model= new DefaultTableModel(listData, tblhead) {
							@Override
							public boolean isCellEditable(int a,int b) {
								   return false;
							}
						};
					    tblBorrowed.setModel(model);
						SwingUtils.showMessage(null,"Success", "test");
					}
				}
			});
		btnCheck.setBounds(10, 17, 107, 27);
		contentPanel.add(btnCheck);
		
		
		model=new DefaultTableModel(null,tblhead);
		tblBorrowed = new JTable();
		tblBorrowed.setModel(model);
		tblBorrowed.setBounds(108, 26, 263, 159);
		contentPanel.add(tblBorrowed);
		JScrollPane jsp = new JScrollPane(tblBorrowed);
		tblBorrowed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==2) {
					int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
					txtISBN.setText(list.get(row).getISBN());
				}
			}
		});
		contentPanel.add(jsp);
		jsp.setBounds(153, 13, 359, 207);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(42, 269, 72, 18);
		contentPanel.add(lblISBN);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
	}
}
