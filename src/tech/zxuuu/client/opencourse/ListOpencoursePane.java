package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

public class ListOpencoursePane extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListOpencoursePane() {
		setLayout(null);
		
		JLabel label = new JLabel("公开课列表");
		label.setBounds(284, 35, 75, 18);
		add(label);
		
		table = new JTable();
		table.setBounds(46, 90, 569, 413);
		add(table);

	}
}
