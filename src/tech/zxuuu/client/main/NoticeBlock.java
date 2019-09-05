package tech.zxuuu.client.main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class NoticeBlock extends JPanel {

	private String title;
	private String date;
	
	/**
	 * Create the panel.
	 */
	public NoticeBlock(String _title, String _date) {
		setBackground(new Color(240, 255, 240));
		this.title = _title;
		this.date = _date;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("·");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(24, 13, 4, 24);
		add(lblNewLabel);
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("宋体", Font.PLAIN, 18));
		lblTitle.setBounds(42, 18, 456, 18);
		add(lblTitle);
		
		JLabel lblNewLabel_1 = new JLabel(date);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(508, 18, 108, 18);
		add(lblNewLabel_1);

	}

}
