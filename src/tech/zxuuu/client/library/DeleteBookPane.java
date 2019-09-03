package tech.zxuuu.client.library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xpath.internal.operations.Bool;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * 书籍删除面板
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
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
				Boolean ret = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.library.BookServer.deleteBook", new Object[] { txtISBN.getText() }).send())
						.getReturn(Boolean.class);

				if (ret) {
					SwingUtils.showMessage(null, "删除成功！", "提示");
				} else {
					SwingUtils.showError(null, "删除失败！", "错误");
				}
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
