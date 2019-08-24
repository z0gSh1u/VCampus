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

public class RenewBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RenewBook frame = new RenewBook();
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
	public RenewBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(115, 52, 86, 24);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(0, 55, 72, 18);
		contentPane.add(lblISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComfirm.setBounds(115, 126, 113, 27);
		contentPane.add(btnComfirm);
	}

}
