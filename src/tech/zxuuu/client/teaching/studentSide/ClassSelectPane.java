package tech.zxuuu.client.teaching.studentSide;

import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;

public class ClassSelectPane extends JPanel {

	private JTable tblClassList;
	private static ConnectionToServer conn;
	private DefaultTableModel model;
	private Panel pBody;
	public String[][] rowData;
	String[] head = { "ID", "课程", "时间", "教师", "教室", "选择状况" };

	public void selectClass(int row) {
		rowData[row][5] = "√";
		DefaultTableModel newModel = new DefaultTableModel(rowData, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		tblClassList.setModel(newModel);
	}

	public void dropCourse(int row) {
		rowData[row][5] = "";
		DefaultTableModel newModel = new DefaultTableModel(rowData, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		tblClassList.setModel(newModel);
	}

	public List<ClassInfo> getClassInfo() {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassInfo",
				new Object[] {App.session.getStudent().getAcademy()});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(ClassInfo.class);
	}

	/**
	 * Create the panel.
	 */
	public ClassSelectPane() {

		this.setLayout(null);

		List<ClassInfo> CI = this.getClassInfo();
		rowData = new String[CI.size()][6];
		for (int i = 0; i < CI.size(); i++) {
			rowData[i][0] = CI.get(i).getID();
			rowData[i][1] = CI.get(i).getClassName();
			rowData[i][2] = CI.get(i).getTime();
			rowData[i][3] = CI.get(i).getTeacher();
			rowData[i][4] = CI.get(i).getClassroom();
			rowData[i][5] = "";
		}
		model = new DefaultTableModel(rowData, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};

		pBody = new Panel();
		pBody.setBounds(10, 0, 534, 458);
		this.add(pBody);
		pBody.setLayout(null);
		tblClassList = new JTable();
		JScrollPane jsp = new JScrollPane(tblClassList);
		jsp.setBounds(14, 13, 518, 425);
		pBody.add(jsp);

		tblClassList.setModel(model);

		tblClassList.getColumnModel().getColumn(0).setPreferredWidth(130);
		tblClassList.getColumnModel().getColumn(0).setMaxWidth(300);
		tblClassList.getColumnModel().getColumn(1).setMaxWidth(214);
		tblClassList.getColumnModel().getColumn(2).setPreferredWidth(136);
		tblClassList.setBounds(5, 5, 512, 390);

		ClassSelectPane csg = this;

		tblClassList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
					if (rowData[row][5] == "") {
						AffirmClassSelectionGUI acsg = new AffirmClassSelectionGUI(csg, row);

						acsg.txtClassID.setText((String) tblClassList.getValueAt(row, 0));
						acsg.btnConfirm.setEnabled(acsg.judgeConflict());
						
						if (!acsg.judgeConflict()) {
							acsg.btnConfirm.setText("课程冲突");
						}
						
						acsg.txtClassName.setText((String) tblClassList.getValueAt(row, 1));
						acsg.txtTime.setText((String) tblClassList.getValueAt(row, 2));
						acsg.txtTeacher.setText((String) tblClassList.getValueAt(row, 3));
						acsg.txtClassroom.setText((String) tblClassList.getValueAt(row, 4));
						acsg.setModal(true);

						acsg.setVisible(true);
					} else {
						DropCourseGUI dcg = new DropCourseGUI(csg, row);
						dcg.txtClassID.setText((String) tblClassList.getValueAt(row, 0));
						dcg.txtClassName.setText((String) tblClassList.getValueAt(row, 1));
						dcg.txtTime.setText((String) tblClassList.getValueAt(row, 2));
						dcg.txtTeacher.setText((String) tblClassList.getValueAt(row, 3));
						dcg.txtClassroom.setText((String) tblClassList.getValueAt(row, 4));
						dcg.setModal(true);
						dcg.setVisible(true);
					}

				}
			}
		});
	}

}
