package tech.zxuuu.client.library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DeleteBookPane extends JPanel {

	private JTextField txtISBN;

	/**
	 * Create the panel.
	 */
	public DeleteBookPane() {
		this.setLayout(null);

		txtISBN = new JTextField();
		txtISBN.setBounds(337, 223, 272, 27);
		this.add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblISBN = new JLabel("图书编号");
		lblISBN.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblISBN.setBounds(164, 219, 140, 33);
		this.add(lblISBN);

		JButton btnComfirm = new JButton("确定");
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Book.deleteBook",
						new Object[] { txtISBN.getText() });
				String hash = req.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response = ResponseQueue.getInstance().consume(hash);
				Boolean ret = response.getReturn(Boolean.class);

				if (ret)
					SwingUtils.showMessage(null, "Succeed deleting", "test");
				else
					SwingUtils.showError(null, "The ISBN is invalid", "test");

			}
		});
		btnComfirm.setBounds(365, 306, 113, 27);
		this.add(btnComfirm);
		
		JLabel label = new JLabel(" 删除书籍");
		label.setIcon(new ImageIcon(DeleteBookPane.class.getResource("/resources/assets/icon/delete.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label.setBounds(45, 40, 245, 64);
		add(label);
	}

}
