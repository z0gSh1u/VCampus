package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtISBN;
	private JTextField txtTitle;
	private JTextField txtAuthor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBook frame = new DeleteBook();
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
	public DeleteBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(89, 57, 86, 24);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(89, 103, 86, 24);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(89, 158, 86, 24);
		contentPane.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(3, 60, 72, 18);
		contentPane.add(lblISBN);
		
		JLabel lblTitle = new JLabel("题名");
		lblTitle.setBounds(3, 103, 72, 18);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(3, 161, 72, 18);
		contentPane.add(lblAuthor);
		
		JButton btnComfirm = new JButton("确定");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComfirm.setBounds(251, 117, 113, 27);
		contentPane.add(btnComfirm);
	}

}
