package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField txtDetails;
	public JTable tblRecommand;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookDetails dialog = new BookDetails();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookDetails() {
		setBounds(100, 100, 492, 347);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		txtDetails = new JTextField();
		txtDetails.setBounds(130, 30, 182, 99);
		getContentPane().add(txtDetails);
		txtDetails.setColumns(10);
		
		JLabel lblDetails = new JLabel("图书简介");
		lblDetails.setBounds(14, 70, 72, 18);
		getContentPane().add(lblDetails);
		
	
		
	
		
		JLabel lblRecommand = new JLabel("相似图书推荐");
		lblRecommand.setBounds(24, 188, 115, 18);
		getContentPane().add(lblRecommand);
		
		String[] book= {"isbn","title","author","number"};
		model=new DefaultTableModel(null,book);
		// recommend
		tblRecommand = new JTable();
		tblRecommand.setModel(model);
		tblRecommand.setBounds(2, 2, 225, 105);
		JScrollPane jsp = new JScrollPane(tblRecommand);
		jsp.setBounds(202, 163, 258, 93);
		getContentPane().add(jsp);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnComfirm.setBounds(34, 214, 113, 27);
		getContentPane().add(btnComfirm);
	}
}
