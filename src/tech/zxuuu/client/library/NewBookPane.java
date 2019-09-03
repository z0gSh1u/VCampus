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
import java.awt.Font;
import javax.swing.ImageIcon;

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
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public NewBookPane() {
		this.setLayout(null);

		txtTitle = new JTextField();
		txtTitle.setBounds(224, 102, 175, 30);
		this.add(txtTitle);
		txtTitle.setColumns(10);

		txtauthor = new JTextField();
		txtauthor.setBounds(591, 102, 175, 30);
		this.add(txtauthor);
		txtauthor.setColumns(10);

		lbl = new JLabel("标题");
		lbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl.setBounds(46, 106, 48, 29);
		this.add(lbl);

		lblAuthor = new JLabel("作者");
		lblAuthor.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblAuthor.setBounds(467, 102, 48, 29);
		this.add(lblAuthor);

		txtSetISBN = new JTextField();
		txtSetISBN.setBounds(224, 153, 175, 30);
		this.add(txtSetISBN);
		txtSetISBN.setColumns(10);

		lblSetISBN = new JLabel("图书编号");
		lblSetISBN.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblSetISBN.setBounds(46, 153, 157, 30);
		this.add(lblSetISBN);

		btnComfirm = new JButton("确定");
		btnComfirm.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		btnComfirm.setBounds(360, 437, 124, 39);
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
		lblDetails.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblDetails.setBounds(48, 230, 88, 30);
		this.add(lblDetails);

		txtCategory = new JTextField();
		txtCategory.setBounds(591, 153, 175, 30);
		this.add(txtCategory);
		txtCategory.setColumns(10);

		lblCategory = new JLabel("分类");
		lblCategory.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblCategory.setBounds(467, 153, 48, 29);
		this.add(lblCategory);

		txtAreaDetails = new JTextArea();
		txtAreaDetails.setBounds(224, 230, 542, 123);
		this.add(txtAreaDetails);

		JLabel lblurl = new JLabel("封面图片URL");
		lblurl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblurl.setBounds(46, 382, 157, 30);
		this.add(lblurl);

		txtPictureURL = new JTextField();
		txtPictureURL.setBounds(224, 380, 542, 35);
		this.add(txtPictureURL);
		txtPictureURL.setColumns(10);
		
		label = new JLabel(" 添加书籍");
		label.setIcon(new ImageIcon(NewBookPane.class.getResource("/resources/assets/icon/add.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label.setBounds(21, 20, 245, 64);
		add(label);
	}

}
