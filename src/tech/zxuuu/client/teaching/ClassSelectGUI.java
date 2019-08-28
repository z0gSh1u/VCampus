package tech.zxuuu.client.teaching;

import java.awt.BorderLayout;
import java.util.Map;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;

public class ClassSelectGUI extends JFrame {

	private static ConnectionToServer conn;
	private JPanel contentPane;
	private JTable tblClassList;
	private DefaultTableModel model;
	private Panel panel_1;
	private Panel pBody;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassSelectGUI frame = new ClassSelectGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public List<ClassInfo> getClassInfo() {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassInfo",
				new Object[] {new Student("a","b", null, null)});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp =ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(ClassInfo.class);
	}

	/**
	 * Create the frame.
	 */
	public ClassSelectGUI() {
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] head = {"ID","课程","时间","教师","教室"};
		List<ClassInfo> CI = this.getClassInfo();
		String[][] rowData= new String [CI.size()][5];
		for (int i=0;i<CI.size();i++) {
			rowData[i][0]=CI.get(i).getID();
			rowData[i][1]=CI.get(i).getClassName();
			rowData[i][2]=CI.get(i).getTime();
			rowData[i][3]=CI.get(i).getTeacher();
			rowData[i][4]=CI.get(i).getClassroom();
		}
		model=new DefaultTableModel(rowData,head);
		
		pBody = new Panel();
		pBody.setBounds(10, 0, 512, 458);
		contentPane.add(pBody);
		pBody.setLayout(null);
		tblClassList = new JTable();
		JScrollPane jsp=new JScrollPane(tblClassList);
		jsp.setBounds(30, 13, 468, 425);
		pBody.add(jsp);
		
		tblClassList.setModel(new DefaultTableModel(rowData,head) {
			public boolean isCellEditable(int a, int b) {
				return false;
			}}
		);

		
		tblClassList.getColumnModel().getColumn(0).setPreferredWidth(130);
		tblClassList.getColumnModel().getColumn(0).setMaxWidth(300);
		tblClassList.getColumnModel().getColumn(1).setMaxWidth(214);
		tblClassList.getColumnModel().getColumn(2).setPreferredWidth(136);
		tblClassList.setBounds(5, 5, 512, 390);
		
		tblClassList.addMouseListener(new MouseAdapter() 
		{ 
		   public void mousePressed(MouseEvent evt) 
		   { 
		        if (evt.getClickCount() == 2) { 
		        	int row =((JTable)evt.getSource()).rowAtPoint(evt.getPoint());
		        	AffirmClassSelectionGUI acsg =new AffirmClassSelectionGUI();
		        	
		        	acsg.txtClassID.setText((String) tblClassList.getValueAt(row, 0));
		        	acsg.txtClassName.setText((String) tblClassList.getValueAt(row, 1));
		        	acsg.txtTime.setText((String) tblClassList.getValueAt(row, 2));
		        	acsg.txtTeacher.setText((String) tblClassList.getValueAt(row, 3));
		        	acsg.txtClassroom.setText((String) tblClassList.getValueAt(row, 4));
		        	acsg.setModal(true);
		        	acsg.setVisible(true);
		        } 
		   } 
		}); 
		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(214, 464, 113, 27);
		contentPane.add(btnReturn);
		
	}
}
