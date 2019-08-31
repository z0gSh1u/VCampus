package tech.zxuuu.client.studentManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentTableGUI extends JFrame {

	private JPanel contentPane;
	private JTable tableKing;
	private JTextField textGrade;
	List<Student> result=null;
	String[][] rowData=null;
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentTableGUI frame = new StudentTableGUI();
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
	public StudentTableGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableKing = new JTable();
		tableKing.setBounds(0, 67, 774, 477);
		String[] head= {"一卡通","学号","院系","姓名"};
		JScrollPane jsp=new JScrollPane(tableKing);
		jsp.setBounds(45, 63, 565, 439);
		model.setDataVector(rowData, head);
		tableKing.setModel(model);
		contentPane.add(jsp);
		
		
		JComboBox comboAcademy = new JComboBox();
		comboAcademy.setBounds(83, 0, 243, 35);
		contentPane.add(comboAcademy);
		
		JLabel lblAcademy = new JLabel("院系");
		lblAcademy.setBounds(21, 3, 48, 29);
		contentPane.add(lblAcademy);
		
		JLabel lblGrade = new JLabel("年级");
		lblGrade.setBounds(369, 3, 57, 29);
		contentPane.add(lblGrade);
		
		textGrade = new JTextField();
		textGrade.setBounds(430, 0, 213, 35);
		contentPane.add(textGrade);
		textGrade.setColumns(10);
		
		JButton buttonSearch = new JButton("查询");
		
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textGrade.getText().length()!=2) {SwingUtils.showMessage(null, "请输入正确的年级！", "错误");}
				else {
					Request request = new Request(
							App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.tableDisplay",
							new Object[] { ((String)comboAcademy.getSelectedItem()).substring(0, 2), textGrade.getText() });
					String hash = request.send();
					ResponseUtils.blockAndWaitResponse(hash);
					Response response = ResponseQueue.getInstance().consume(hash);
					result = response.getListReturn(Student.class);
					rowData=new String [result.size()][4];
					for (int i=0;i<result.size();i++) {
						rowData[i][0]=result.get(i).getCardNumber();
						rowData[i][1]=result.get(i).getStudentNumber();
						rowData[i][2]=result.get(i).getAcademy();
						rowData[i][3]=result.get(i).getName();
					}
					model.setDataVector(rowData,head);
					// System.out.println(result);
				}
			}
		});
		buttonSearch.setBounds(662, -1, 101, 37);
		contentPane.add(buttonSearch);
		
		List<String> academies = new ArrayList<>(
				Arrays.asList(
					"01","02","03","04","05","06","07","08","09","10","11","12",
					"13","14","15","16","17","19","21","22","24","25","41","42","43","57","61","71","88")
				);
			for (String academy : academies) {
				comboAcademy.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
			}
	}
}
