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

public class RenewBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JTable tblBeborrowed;
	private DefaultTableModel model;
	private JButton btnCheck;
	private List<Book> list=null;
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
		txtISBN.setEditable(false);
		txtISBN.setBounds(80, 256, 105, 24);
		
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(34, 259, 32, 18);
		contentPanel.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.setBounds(80, 313, 63, 27);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
					    tblBeborrowed.setModel(model);
						SwingUtils.showMessage(null,"Success", "test");
					}
				}
			});
		btnCheck.setBounds(34, 17, 107, 27);
		contentPanel.add(btnCheck);
	
		
		model=new DefaultTableModel(null,tblhead);
		tblBeborrowed = new JTable();
		tblBeborrowed.setModel(model);
		tblBeborrowed.setBounds(2, 2, 300, 300);
		
		JScrollPane jsp = new JScrollPane(tblBeborrowed);
		tblBeborrowed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==2) {
					int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
		            txtISBN.setText(list.get(row).getISBN());
		            }
		}});
		jsp.setLocation(180, 13);
		jsp.setSize(340, 199);
		contentPanel.add(jsp);
	}

}
