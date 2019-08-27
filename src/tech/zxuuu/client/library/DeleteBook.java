package tech.zxuuu.client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBook frame = new DeleteBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(89, 57, 86, 24);
		contentPane.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(3, 60, 72, 18);
		contentPane.add(lblISBN);
		
		JButton btnComfirm = new JButton("确定");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Book.deleteBook", 
						new Object[] {txtISBN.getText()});
					String hash = req.send();
					ResponseUtils.blockAndWaitResponse(hash);
					Response response = ResponseQueue.getInstance().consume(hash);
					Boolean ret = response.getReturn(Boolean.class);
					SwingUtils.showMessage(null, "Succeed deleting", "test");
			}
		});
		btnComfirm.setBounds(88, 139, 113, 27);
		contentPane.add(btnComfirm);
	}

}
