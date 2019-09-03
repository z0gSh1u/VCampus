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

public class ListOpencoursePane extends JPanel {
	private JTable table;
	private DefaultTableModel model=new DefaultTableModel();
	
	public List<OpenCourseInfo> getOpenCourseInfos() {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.OpencoureManage.getClassInfos",
				new Object[] {App.session.getStudent().getAcademy()});
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(OpenCourseInfo.class);
	}

	/**
	 * Create the panel.
	 */
	public ListOpencoursePane() {
		setLayout(null);
		
		JLabel label = new JLabel("公开课列表");
		label.setBounds(284, 35, 75, 18);
		add(label);
		
		table = new JTable();
		String[] head = { "ID", "课程名", "演讲者", "预告","录像" };
		
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(46, 90, 600, 450);
		List<OpenCourseInfo> oci= ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.opencourse.OpencourseManage.getOpencourseInfos",
						null).send())
				.getListReturn(OpenCourseInfo.class);
		String [][] rowDate=new String [oci.size()][5];
		System.out.println(oci.size());
		for (int i=0;i<oci.size();i++) {
			rowDate[i][0]=String.valueOf(oci.get(i).getId());
			rowDate[i][1]=oci.get(i).getCourseName();
			rowDate[i][2]=oci.get(i).getSpeaker();
			rowDate[i][3]=oci.get(i).getPreview();
			rowDate[i][4]=oci.get(i).getVideo();
		}
		model.setDataVector(rowDate, head);
		table.setModel(model);
		table.setBounds(46, 90, 569, 413);
		add(jsp);

	}
}
