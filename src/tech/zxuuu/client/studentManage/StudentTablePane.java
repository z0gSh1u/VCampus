package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 学生列表功能面板
 * 
 * @version 已review完成，已美化
 */
public class StudentTablePane extends JPanel {

	private JTable infoTable;
	private JTextField textGrade;
	List<Student> result = null;
	String[][] rowData = null;
	DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int a, int b) {
			return false;
		}
	};

	/**
	 * Create the panel.
	 */
	public StudentTablePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(null);
		infoTable = new JTable();
		infoTable.setBounds(0, 67, 774, 477);
		String[] head = { "一卡通", "学号", "院系", "姓名" };
		JScrollPane jsp = new JScrollPane(infoTable);
		jsp.setBounds(44, 91, 572, 439);
		model.setDataVector(rowData, head);
		infoTable.setModel(model);
		this.add(jsp);
		JComboBox comboAcademy = new JComboBox();
		comboAcademy.setBounds(91, 43, 243, 35);
		this.add(comboAcademy);

		JLabel lblAcademy = new JLabel("院系");
		lblAcademy.setBounds(47, 51, 30, 18);
		this.add(lblAcademy);

		JLabel lblGrade = new JLabel("年级");
		lblGrade.setBounds(348, 51, 30, 18);
		this.add(lblGrade);

		textGrade = new JTextField();
		textGrade.setBounds(392, 43, 121, 35);
		this.add(textGrade);
		textGrade.setColumns(10);

		JButton buttonSearch = new JButton("查询");

		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textGrade.getText().length() != 2) {
					SwingUtils.showMessage(null, "请输入正确的年级！", "错误");
				} else {
					result = ResponseUtils.getResponseByHash(
							new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.tableDisplay",
									new Object[] { ((String) comboAcademy.getSelectedItem()).substring(0, 2), textGrade.getText() })
											.send())
							.getListReturn(Student.class);
					if (result.size() == 0) {
						SwingUtils.showMessage(null, "该条件下查询结果为空。", "提示");
						return;
					}
					rowData = new String[result.size()][4];
					for (int i = 0; i < result.size(); i++) {
						rowData[i][0] = result.get(i).getCardNumber();
						rowData[i][1] = result.get(i).getStudentNumber();
						rowData[i][2] = result.get(i).getAcademy();
						rowData[i][3] = result.get(i).getName();
					}
					model.setDataVector(rowData, head);
				}
			}
		});
		buttonSearch.setBounds(527, 42, 82, 36);
		this.add(buttonSearch);

		List<String> academies = new ArrayList<>(
				Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
						"17", "19", "21", "22", "24", "25", "41", "42", "43", "57", "61", "71", "88"));
		for (String academy : academies) {
			comboAcademy.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}
	}

}
