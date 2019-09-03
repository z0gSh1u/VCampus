package tech.zxuuu.client.library;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HotBook extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public HotBook(String PictureURL,String title,String author,int number) {
		setLayout(null);
		
		JEditorPane editPicture = new JEditorPane();
		editPicture.setBounds(65, 13, 188, 337);
		editPicture.setContentType("text/html");
		add(editPicture);
		editPicture.setText("<html><body><img src=\""+PictureURL+"\"></body><html>");
		
		JLabel lblPicture = new JLabel("图书封面");
		lblPicture.setBounds(0, 42, 72, 18);
		add(lblPicture);
		
		JLabel lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(267, 107, 72, 18);
		add(lblAuthor);
		
		JLabel label = new JLabel("书名");
		label.setBounds(267, 9, 72, 18);
		add(label);
		
		JTextArea txtTitle = new JTextArea();
		txtTitle.setBounds(267, 40, 137, 54);
		txtTitle.setText(title);
		add(txtTitle);
		
		JTextArea txtAuthor = new JTextArea();
		txtAuthor.setBounds(267, 148, 137, 50);
		txtAuthor.setText(author);
		add(txtAuthor);
		
		JLabel label_1 = new JLabel("热度");
		label_1.setBounds(267, 211, 72, 18);
		add(label_1);
		
		JTextArea txtNumber = new JTextArea();
		txtNumber.setBounds(267, 260, 137, 50);
		txtNumber.setText(String.valueOf(number));
		add(txtNumber);

	}
}
