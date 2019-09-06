package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import java.awt.Font;


/**
 * 公开课列表
 * 
 * @author z0gSh1u
 * @author 王志华
 */
public class ListOpencoursePane extends JPanel {
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();


	public void updateOpenCourse() {
		List<OpenCourseInfo> oci = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.opencourse.OpencourseManage.getOpenCourseList", null).send())
				.getListReturn(OpenCourseInfo.class);
		String[][] rowDate = new String[oci.size()][5];
		System.out.println(oci.size());
		String[] head = { "ID", "课程名", "演讲者", "预告", "录像" };
		for (int i = 0; i < oci.size(); i++) {
			rowDate[i][0] = String.valueOf(oci.get(i).getId());
			rowDate[i][1] = oci.get(i).getCourseName();
			rowDate[i][2] = oci.get(i).getSpeaker();
			rowDate[i][3] = oci.get(i).getPreview();
			rowDate[i][4] = oci.get(i).getVideo();
		}
		model.setDataVector(rowDate, head);
	}
	public ListOpencoursePane() {
		setLayout(null);
		JLabel label = new JLabel("公开课列表");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(270, 30, 90, 24);
		add(label);
		table = new JTable();
		table.setEnabled(false);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(31, 72, 600, 450);
		updateOpenCourse();

		table.setModel(model);
		table.setBounds(46, 90, 569, 413);
		add(jsp);
	}
}
