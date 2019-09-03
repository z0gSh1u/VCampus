package tech.zxuuu.client.library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

public class NewBookPane extends JPanel {

	private JTextField txtTitle;
	private JTextField txtauthor;
	private JLabel lbl;
	private JLabel lblAuthor;
	private JTextField txtSetISBN;
	private JLabel lblSetISBN;
	private JButton btnComfirm;
	private JTextField txtCategory;
	private JLabel lblCategory;
	private JTextArea txtAreaDetails;
	private JTextField txtPictureURL;

	/**
	 * Create the panel.
	 */
	public NewBookPane() {
		this.setLayout(null);

		txtTitle = new JTextField();
		txtTitle.setBounds(107, 57, 86, 24);
		this.add(txtTitle);
		txtTitle.setColumns(10);

		txtauthor = new JTextField();
		txtauthor.setBounds(107, 104, 86, 24);
		this.add(txtauthor);
		txtauthor.setColumns(10);

		lbl = new JLabel("题名");
		lbl.setBounds(0, 60, 72, 18);
		this.add(lbl);

		lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(3, 111, 72, 18);
		this.add(lblAuthor);

		txtSetISBN = new JTextField();
		txtSetISBN.setBounds(107, 162, 86, 24);
		this.add(txtSetISBN);
		txtSetISBN.setColumns(10);

		lblSetISBN = new JLabel("设置ISBN");
		lblSetISBN.setBounds(0, 165, 72, 18);
		this.add(lblSetISBN);

		btnComfirm = new JButton("确定");
		btnComfirm.setBounds(290, 276, 113, 27);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.BookServer.addBook",

						new Object[] { txtSetISBN.getText(), txtTitle.getText(), txtauthor.getText(), txtCategory.getText(),
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
		this.add(btnComfirm);

		JLabel lblDetails = new JLabel("图书详情");
		lblDetails.setBounds(207, 77, 72, 18);
		this.add(lblDetails);

		txtCategory = new JTextField();
		txtCategory.setBounds(107, 214, 86, 24);
		this.add(txtCategory);
		txtCategory.setColumns(10);

		lblCategory = new JLabel("类别");
		lblCategory.setBounds(3, 217, 72, 18);
		this.add(lblCategory);

		txtAreaDetails = new JTextArea();
		txtAreaDetails.setBounds(284, 60, 157, 72);
		this.add(txtAreaDetails);

		JLabel lblurl = new JLabel("图片URL");
		lblurl.setBounds(207, 190, 72, 18);
		this.add(lblurl);

		txtPictureURL = new JTextField();
		txtPictureURL.setBounds(290, 199, 151, 39);
		this.add(txtPictureURL);
		txtPictureURL.setColumns(10);
	}

}
