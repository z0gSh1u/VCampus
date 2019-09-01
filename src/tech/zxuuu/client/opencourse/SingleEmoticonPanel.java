package tech.zxuuu.client.opencourse;

import javax.swing.JPanel;
import javax.swing.JEditorPane;

public class SingleEmoticonPanel extends JPanel {
	private String name;
	private String URL;

	/**
	 * Create the panel.
	 */
	public SingleEmoticonPanel() {
		setLayout(null);

		JEditorPane epnEmoticon = new JEditorPane();
		epnEmoticon.setEditable(false);
		epnEmoticon.setContentType("text/html");
		epnEmoticon.setBounds(0, 0, 236, 219);
		add(epnEmoticon);

		name = URL = "";
	}

}
