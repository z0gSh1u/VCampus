package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;

public class LibraryQuery extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblSearch;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryQuery frame = new LibraryQuery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibraryQuery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(113, 47, 128, 24);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblTitle = new JLabel("题名");
		lblTitle.setBounds(0, 50, 72, 18);
		contentPane.add(lblTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setText("");
		txtAuthor.setBounds(399, 47, 128, 24);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblAuthor = new JLabel("责任者");
		lblAuthor.setBounds(313, 53, 72, 18);
		contentPane.add(lblAuthor);
		
		JButton btnSearch = new JButton("检索");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Book.searchAuthorByTitle", 
					new Object[] {txtTitle.getText()});
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);
				String ret = response.getReturn(String.class);
				SwingUtils.showMessage(null, ret, "test");
			}
		});
		btnSearch.setBounds(53, 159, 113, 27);
		contentPane.add(btnSearch);
		
		JButton btnReset = new JButton("重置");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setBounds(218, 159, 113, 27);
		contentPane.add(btnReset);
		
		String[] book= {"isbn","title","author"};
		model=new DefaultTableModel(null,book);
		tblSearch = new JTable();
		JScrollPane jsp = new JScrollPane(tblSearch);
		jsp.setLocation(83, 255);
		jsp.setSize(415, 200);
		contentPane.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);
		
	}
}
