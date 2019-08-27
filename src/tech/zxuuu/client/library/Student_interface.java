package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Student_interface extends JFrame {

	private JPanel contentPane;
	private JTable tblPopular;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_interface frame = new Student_interface();
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
	public Student_interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBorrow = new JButton("借书");
		btnBorrow.setBounds(35, 75, 113, 27);
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryQuery lq = new LibraryQuery();
				lq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lq.setVisible(true);//打开另一个窗口
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBorrow);
		
		JButton btnReturn = new JButton("还书");
		btnReturn.setBounds(35, 153, 113, 27);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return re=new Return();
				re.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				re.setVisible(true);
			}
		});
		contentPane.add(btnReturn);
		
		JButton btnPopular = new JButton("热门书籍");
		btnPopular.setBounds(35, 220, 113, 27);
		btnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnPopular);
		
	    String []popularBook= {"isbn","title","author","number"};
	    model=new DefaultTableModel(null,popularBook);
		tblPopular= new JTable();
		JScrollPane jsp = new JScrollPane(tblPopular);
		jsp.setBounds(201, 165, 325, 127);
		contentPane.add(jsp);
		tblPopular.setModel(model);
		tblPopular.setBounds(2, 2, 300, 300);
		
		JButton btnRenew = new JButton("续借");
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RenewBook renew=new RenewBook();
				renew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				renew.setVisible(true);
			}
		});
		btnRenew.setBounds(35, 309, 113, 27);
		contentPane.add(btnRenew);
		
		
		
		
	}
}
