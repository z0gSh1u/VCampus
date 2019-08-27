package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;

public class TeachingAdmGUI extends JFrame {

	private JPanel contentPane;
	private JTable tblClassList;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeachingAdmGUI frame = new TeachingAdmGUI();
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
	public TeachingAdmGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pBody = new JPanel();
		pBody.setBounds(5, 5, 500, 436);
		pBody.setLayout(null);
		contentPane.add(pBody);
		
		JLabel lbTitle = new JLabel("VCampus教务管理系统");
		lbTitle.setBounds(187, 8, 146, 18);
		pBody.add(lbTitle);
		
		JLabel lbVer = new JLabel("管理员版");
		lbVer.setBounds(423, 8, 71, 18);
		pBody.add(lbVer);
		
		JButton btnNew = new JButton("新增课程");
		btnNew.setBounds(81, 454, 93, 27);
		contentPane.add(btnNew);
		
		JButton btnDelete = new JButton("课程删除");
		btnDelete.setBounds(206, 454, 93, 27);
		contentPane.add(btnDelete);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(335, 455, 93, 27);
		contentPane.add(btnReturn);
		
		tblClassList = new JTable();
		JScrollPane jsp=new JScrollPane(tblClassList);
		jsp.setBounds(32, 34, 468, 374);
		pBody.add(jsp);
		
		String[] head = {"ID","课程","时间","教师","教室"};
		String[][] rowData= {{"09001112014203","大英3","周一 8：00-9：35","孔辉湘","教4-203"}};
		model=new DefaultTableModel(rowData,head);
		
		tblClassList.setModel(new DefaultTableModel(rowData,head));
		
		tblClassList.getColumnModel().getColumn(0).setPreferredWidth(130);
		tblClassList.getColumnModel().getColumn(0).setMaxWidth(300);
		tblClassList.getColumnModel().getColumn(1).setMaxWidth(214);
		tblClassList.getColumnModel().getColumn(2).setPreferredWidth(136);
		tblClassList.setBounds(5, 5, 512, 390);
	}

}
