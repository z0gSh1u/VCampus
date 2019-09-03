package tech.zxuuu.client.library;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Font;

public class HotBook extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public HotBook(String PictureURL,String title,String author,int number) {
		setLayout(null);
		
		JEditorPane editPicture = new JEditorPane();
		editPicture.setEditable(false);
		editPicture.setBounds(21, 33, 185, 260);
		editPicture.setContentType("text/html");
		add(editPicture);
		editPicture.setText("<html><body><img src=\""+PictureURL+"\"></body><html>");
		
		JLabel lblAuthor = new JLabel("");
		lblAuthor.setIcon(new ImageIcon(HotBook.class.getResource("/resources/assets/icon/迎宾 (2).png")));
		lblAuthor.setBounds(216, 141, 32, 32);
		add(lblAuthor);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(HotBook.class.getResource("/resources/assets/icon/名单 (1).png")));
		label.setBounds(216, 53, 32, 32);
		add(label);
		
		JTextArea txtTitle = new JTextArea();
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtTitle.setBounds(260, 46, 140, 55);
		txtTitle.setText(title);
		add(txtTitle);
		
		JTextArea txtAuthor = new JTextArea();
		txtAuthor.setEditable(false);
		txtAuthor.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtAuthor.setBounds(259, 134, 140, 55);
		txtAuthor.setText(author);
		add(txtAuthor);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(HotBook.class.getResource("/resources/assets/icon/火 (1).png")));
		label_1.setBounds(216, 232, 32, 32);
		add(label_1);
		
		JTextArea txtNumber = new JTextArea();
		txtNumber.setEditable(false);
		txtNumber.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtNumber.setBounds(261, 227, 140, 55);
		txtNumber.setText(String.valueOf(number));
		add(txtNumber);

	}
}
