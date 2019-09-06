package tech.zxuuu.client.main;

import javax.swing.JPanel;

import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NoticeBlock extends JPanel {

	private String title;
	private String date;
	private String url;

	/**
	 * Create the panel.
	 */
	public NoticeBlock(String _title, String _date, String _url) {
		setBackground(new Color(240, 255, 240));
		this.title = _title;
		this.date = _date;
		this.url = _url;
		setLayout(null);

		JLabel lblDot = new JLabel("·");
		lblDot.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDot.setBounds(24, 13, 4, 24);
		add(lblDot);

		NoticeBlock that = this;
		JLabel lblTitle = new JLabel(title);
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Open URL
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(that.url));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "链接打开失败！", "错误");
				}
			}
		});
		lblTitle.setFont(new Font("宋体", Font.PLAIN, 18));
		lblTitle.setBounds(42, 18, 513, 18);
		add(lblTitle);

		JLabel lblDate = new JLabel(date);
		lblDate.setFont(new Font("宋体", Font.PLAIN, 18));
		lblDate.setBounds(556, 16, 108, 18);
		add(lblDate);

	}

}
