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

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtauthor;
	private JLabel lbl;
	private JLabel lblAuthor;
	private JTextField txtSetISBN;
	private JLabel lblSetISBN;
	private JButton btnComfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(107, 57, 86, 24);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtauthor = new JTextField();
		txtauthor.setBounds(107, 104, 86, 24);
		contentPane.add(txtauthor);
		txtauthor.setColumns(10);
		
		lbl = new JLabel("题名");
		lbl.setBounds(0, 60, 72, 18);
		contentPane.add(lbl);
		
		lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(3, 111, 72, 18);
		contentPane.add(lblAuthor);
		
		txtSetISBN = new JTextField();
		txtSetISBN.setBounds(107, 162, 86, 24);
		contentPane.add(txtSetISBN);
		txtSetISBN.setColumns(10);
		
		lblSetISBN = new JLabel("设置ISBN");
		lblSetISBN.setBounds(0, 165, 72, 18);
		contentPane.add(lblSetISBN);
		
		btnComfirm = new JButton("确定");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComfirm.setBounds(280, 213, 113, 27);
		contentPane.add(btnComfirm);
	}
}
