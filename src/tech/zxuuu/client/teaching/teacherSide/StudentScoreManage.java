package tech.zxuuu.client.teaching.teacherSide;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.teaching.studentSide.AffirmClassSelectionGUI;
import tech.zxuuu.client.teaching.studentSide.DropCourseGUI;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;

/**
 * 学生成绩管理第一页（教师开课全列表）
 * 
 * @author z0gSh1u
 */
public class StudentScoreManage extends JPanel {
	private JTable table;

	String[] head = { "ID", "课程", "时间", "教师", "教室" };
	private DefaultTableModel model;
	public String[][] rowData;

	public List<ClassInfo> getClassOfOneTeacher(String card) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.getClassOfOneTeacher", new Object[] { card }).send())
				.getListReturn(ClassInfo.class);
	}

	/**
	 * Create the panel.
	 */
	public StudentScoreManage() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 42, 856, 562);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		List<ClassInfo> list = getClassOfOneTeacher(App.session.getTeacher().getCardNumber());

		if (list == null || list.size() == 0) {
			SwingUtils.showError(null, "该教师未开课！", "错误");
			model = new DefaultTableModel(null, head) {
				@Override
				public boolean isCellEditable(int a, int b) {
					return false;
				}
			};
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(130);
		} else {
			rowData = new String[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				rowData[i][0] = list.get(i).getID();
				rowData[i][1] = list.get(i).getClassName();
				rowData[i][2] = list.get(i).getTime();
				rowData[i][3] = list.get(i).getTeacher();
				rowData[i][4] = list.get(i).getClassroom();
				model = new DefaultTableModel(rowData, head) {
					@Override
					public boolean isCellEditable(int a, int b) {
						return false;
					}
				};
				table.setModel(model);
				table.getColumnModel().getColumn(0).setPreferredWidth(140);
				table.getColumnModel().getColumn(2).setPreferredWidth(130);
			}
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
					String classId = list.get(row).getId();

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							new ScoreDetailSetting(classId, list.get(row).getClassName()).setVisible(true);
						}
					});

				}
			}
		});

	}
}
