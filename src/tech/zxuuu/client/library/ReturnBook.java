package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * 书籍归还窗口
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class ReturnBook extends JDialog {

	private final JPanel lblISBN = new JPanel();
	private JTextField txtISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReturnBook dialog = new ReturnBook();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReturnBook() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReturnBook.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setTitle("书籍归还 - VCampus");
		setBounds(100, 100, 530, 237);
		getContentPane().setLayout(new BorderLayout());
		lblISBN.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(lblISBN, BorderLayout.CENTER);
		lblISBN.setLayout(null);

		JLabel lblIsbn = new JLabel("请输入书籍编号");
		lblIsbn.setBounds(54, 60, 118, 18);
		lblISBN.add(lblIsbn);

		txtISBN = new JTextField();
		txtISBN.setFont(new Font("宋体", Font.PLAIN, 16));
		txtISBN.setBounds(171, 50, 269, 37);
		lblISBN.add(txtISBN);
		txtISBN.setColumns(10);

		JButton btnComfirm = new JButton("确认");
		btnComfirm.setIcon(new ImageIcon(ReturnBook.class.getResource("/resources/assets/icon/tick.png")));
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, App.session, "tech.zxuuu.server.library.BookServer.returnBook",
										new Object[] { App.session.getStudent().getCardNumber(), txtISBN.getText() }).send())
						.getReturn(Integer.class);
				if (result == 2) {
					SwingUtils.showMessage(null, "还书成功！", "提示");
				}
				if (result == 1) {
					SwingUtils.showError(null, "你不能归还本书！", "错误");
				}
				if (result == 0) {
					SwingUtils.showError(null, "无效的书目编码！", "错误");
				}
			}

		});
		btnComfirm.setBounds(181, 110, 115, 57);
		lblISBN.add(btnComfirm);

		JLabel lblNewLabel = new JLabel("书籍归还");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(196, 13, 72, 24);
		lblISBN.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}

	}
}
