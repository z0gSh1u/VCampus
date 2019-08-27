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

public class RenewBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JTable tblBeborrowed;
	private DefaultTableModel model;
	private JButton btnCheck;
	private JTextField txtName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RenewBook dialog = new RenewBook();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RenewBook() {
		setBounds(100, 100, 623, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		contentPanel.setLayout(null);
		txtISBN = new JTextField();
		txtISBN.setBounds(151, 11, 86, 24);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(60, 14, 32, 18);
		contentPanel.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(151, 69, 63, 27);
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPanel.add(btnComfirm);
		
		String[] head= {"isbn","title","author"};
		
		btnCheck = new JButton("查看所借书");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.searchBeBorrowed", 
						new Object[] {txtName.getText()});
					String hash = req.send();
					ResponseUtils.blockAndWaitResponse(hash);
					Response response = ResponseQueue.getInstance().consume(hash); 
					List<Book> list =response.getListReturn(Book.class);
					model.setRowCount(0);
					// 填充新数据
					for (int i = 0; i < list.size(); i++) {
						Object[] toAdd = new Object[] {
								list.get(i).getISBN(), list.get(i).getTitle(), list.get(i).getAuthor()
						};
						model.addRow(toAdd);
					}
			}					
		});
		btnCheck.setBounds(89, 280, 107, 27);
		contentPanel.add(btnCheck);
	
		model=new DefaultTableModel(null,head);

	
		tblBeborrowed = new JTable();
		tblBeborrowed.setModel(model);
		tblBeborrowed.setBounds(2, 2, 300, 300);
		
		JScrollPane jsp = new JScrollPane(tblBeborrowed);
		jsp.setLocation(210, 127);
		jsp.setSize(320, 199);
		contentPanel.add(jsp);
		

		
		txtName = new JTextField();
		txtName.setBounds(107, 227, 86, 24);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(60, 230, 30, 18);
		contentPanel.add(lblName);
	}

}
