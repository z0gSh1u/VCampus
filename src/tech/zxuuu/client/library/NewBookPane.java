package tech.zxuuu.client.library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.control.ComboBox;
import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.JComboBox;

public class NewBookPane extends JPanel {

	private JTextField txtTitle;
	private JTextField txtauthor;
	private JLabel lbl;
	private JLabel lblAuthor;
	private JTextField txtSetISBN;
	private JLabel lblSetISBN;

	private JLabel lblCategory;
	private JTextArea txtAreaDetails;
	private JTextField txtPictureURL;
	private JComboBox<String> combCategory;

	/**
	 * Create the panel.
	 */
	public NewBookPane() {
		this.setLayout(null);

		txtTitle = new JTextField();
		txtTitle.setBounds(107, 74, 86, 24);
		this.add(txtTitle);
		txtTitle.setColumns(10);

		txtauthor = new JTextField();
		txtauthor.setBounds(107, 125, 86, 24);
		this.add(txtauthor);
		txtauthor.setColumns(10);

		lbl = new JLabel("题名");
		lbl.setBounds(3, 77, 72, 18);
		this.add(lbl);

		lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(3, 128, 72, 18);
		this.add(lblAuthor);

		txtSetISBN = new JTextField();
		txtSetISBN.setBounds(107, 187, 86, 24);
		this.add(txtSetISBN);
		txtSetISBN.setColumns(10);

		lblSetISBN = new JLabel("设置ISBN");
		lblSetISBN.setBounds(3, 190, 72, 18);
		this.add(lblSetISBN);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.addBook",
                    new Object[] { txtSetISBN.getText(), txtTitle.getText(), txtauthor.getText(), combCategory.getSelectedItem(),
								txtAreaDetails.getText(), txtPictureURL.getText() });
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);
				Boolean ret = response.getReturn(Boolean.class);

				if (ret)
					SwingUtils.showMessage(null, "Succeed adding", "test");
				else {
					SwingUtils.showError(null, "Fail adding maybe invalid ISBN", "test");

			}
			}
		});
		btnComfirm.setBounds(284, 260, 113, 27);
		add(btnComfirm);

		JLabel lblDetails = new JLabel("图书详情");
		lblDetails.setBounds(207, 77, 72, 18);
		this.add(lblDetails);

		lblCategory = new JLabel("类别");
		lblCategory.setBounds(0, 28, 72, 18);
		this.add(lblCategory);

		txtAreaDetails = new JTextArea();
		txtAreaDetails.setBounds(284, 60, 157, 72);
		this.add(txtAreaDetails);

		JLabel lblurl = new JLabel("图片URL");
		lblurl.setBounds(207, 190, 72, 18);
		this.add(lblurl);

		txtPictureURL = new JTextField();
		txtPictureURL.setBounds(284, 190, 151, 39);
		this.add(txtPictureURL);
		txtPictureURL.setColumns(10);
		
		combCategory = new JComboBox();
		combCategory.setBounds(107, 25, 137, 24);
		combCategory.addItem("文学");
		combCategory.addItem("科学技术");
		combCategory.addItem("体育");
		combCategory.addItem("哲学");
		combCategory.setVisible(true);
		add(combCategory);
		
	}
}	

