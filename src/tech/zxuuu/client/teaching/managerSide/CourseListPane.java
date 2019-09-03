package tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseListPane extends JPanel {
	private JTable table;
	
	String[] head = { "ID", "课程", "时间", "教师", "教室"};
	public String[][] rowData;
	private DefaultTableModel model;

	public static List<ClassInfo> getClassInfo(String academy) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassInfo",
				new Object[] {academy});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(ClassInfo.class);
	}
	
	/**
	 * Create the panel.
	 */
	public CourseListPane() {
		setLayout(null);
		
		JLabel label = new JLabel("课程列表");
		label.setBounds(278, 13, 72, 18);
		add(label);
		
		JLabel label_1 = new JLabel("选择院系");
		label_1.setBounds(55, 58, 72, 18);
		add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(141, 55, 340, 24);
		add(comboBox);
		
		List<String> academies = new ArrayList<>(
				Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
						"17", "19", "21", "22", "24", "25", "41", "42", "43", "57", "61", "71"));
		for (String academy : academies) {
			comboBox.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 96, 610, 441);
		add(scrollPane);
		
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// table.setModel(model);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				List<ClassInfo> CI = getClassInfo(((String)comboBox.getSelectedItem()).substring(0, 2));
				rowData = new String[CI.size()][5];
				for (int i = 0; i < CI.size(); i++) {
					rowData[i][0] = CI.get(i).getID();
					rowData[i][1] = CI.get(i).getClassName();
					rowData[i][2] = CI.get(i).getTime();
					rowData[i][3] = CI.get(i).getTeacher();
					rowData[i][4] = CI.get(i).getClassroom();
				}
				model = new DefaultTableModel(rowData, head) {
					@Override
					public boolean isCellEditable(int a, int b) {
						return false;
					}
				};
				table.setModel(model);
				
			}
		});
		btnNewButton.setBounds(507, 54, 113, 27);
		add(btnNewButton);

	}
}
