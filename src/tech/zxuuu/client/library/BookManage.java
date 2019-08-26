package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookManage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManage frame = new BookManage();
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
	public BookManage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddBook = new JButton("书籍入库");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBook add = new AddBook();
				add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				add.setVisible(true);//打开另一个窗口
				
			}
		});
		btnAddBook.setBounds(138, 53, 113, 27);
		contentPane.add(btnAddBook);
		
		JButton btnDeleteBook = new JButton("书籍出库");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteBook delete=new DeleteBook();
				delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				delete.setVisible(true);
			}
		});
		btnDeleteBook.setBounds(138, 122, 113, 27);
		contentPane.add(btnDeleteBook);
	}
}
