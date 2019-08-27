package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Return extends JFrame {

	private JPanel btnConfirm;
	private JTextField txtISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return frame = new Return();
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
	public Return() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		btnConfirm = new JPanel();
		btnConfirm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(btnConfirm);
		btnConfirm.setLayout(null);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(139, 49, 86, 24);
		btnConfirm.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(29, 55, 72, 18);
		btnConfirm.add(lblISBN);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(139, 152, 113, 27);
		btnConfirm.add(button);
	}

}
