package tech.zxuuu.client.teaching.studentSide;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class QueryScorePane extends JPanel {
	private JTable table;
	private String[][] rowData;
	String[] head = { "课程Id", "课程名", "教师", "给分" };
	private DefaultTableModel model;

	public ClassInfo getOneClass(String ID) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { ID }).send())
				.getReturn(ClassInfo.class);
	}

	/**
	 * Create the panel.
	 */
	public QueryScorePane() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 69, 867, 505);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		String raw = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
						new Object[] { App.session.getStudent() }).send())
				.getReturn(String.class);
		String[] L1 = raw.split(",");

		if (raw == null || raw.length() == 0 || raw.equals(',')) {
			model = new DefaultTableModel(null, head) {
				@Override
				public boolean isCellEditable(int a, int b) {
					return false;
				}
			};
		} else {
			List<String> corrScore = ResponseUtils
					.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.Addons.getScoreList",
							new Object[] { App.session.getStudent().getCardNumber(), raw }).send())
					.getListReturn(String.class);
			rowData = new String[corrScore.size()][4];
			for (int i = 0; i < corrScore.size(); i++) {
				rowData[i][0] = L1[i];
				ClassInfo ci = getOneClass(L1[i]);
				rowData[i][1] = ci.getClassName();
				rowData[i][2] = ci.getTeacher();
				rowData[i][3] = corrScore.get(i).equals("xxx") ? "未打分" : corrScore.get(i);
			}
			model = new DefaultTableModel(rowData, head) {
				@Override
				public boolean isCellEditable(int a, int b) {
					return false;
				}
			};
		}

		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("重新进入教务平台以刷新成绩信息");
		lblNewLabel.setBounds(656, 587, 225, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程成绩");
		lblNewLabel_1.setIcon(new ImageIcon(QueryScorePane.class.getResource("/resources/assets/icon/cj.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(385, 13, 124, 48);
		add(lblNewLabel_1);

	}
}
