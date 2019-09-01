package tech.zxuuu.client.main.shortcut;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class shortcutDefault extends JPanel {

	/**
	 * Create the panel.
	 */
	public shortcutDefault() {
		setLayout(null);
		
		JLabel label = new JLabel("选择一项功能以使用捷径...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(114, 167, 249, 18);
		add(label);

	}

}
