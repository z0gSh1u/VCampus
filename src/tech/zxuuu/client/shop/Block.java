package tech.zxuuu.client.shop;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Block extends JPanel {

	/**
	 * Create the panel.
	 */
	public Block() {
		setLayout(null);
		
		JLabel lbl_Picture = new JLabel("图片");
		lbl_Picture.setBounds(28, 26, 176, 137);
		add(lbl_Picture);


	}
	
}
