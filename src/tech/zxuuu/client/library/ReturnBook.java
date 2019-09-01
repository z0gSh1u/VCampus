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
		setBounds(100, 100, 530, 362);
		getContentPane().setLayout(new BorderLayout());
		lblISBN.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(lblISBN, BorderLayout.CENTER);
		lblISBN.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(35, 47, 72, 18);
		lblISBN.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(76, 44, 86, 24);
		lblISBN.add(txtISBN);
		txtISBN.setColumns(10);
		
		JButton btnComfirm = new JButton("确认");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request request=new Request(App.connectionToServer,App.session,"tech.zxuuu.server.library.BookServer.returnBook",
						new Object[] {txtISBN.getText()});
				String hash=request.send();
				ResponseUtils.blockAndWaitResponse(hash);
				Response response=ResponseQueue.getInstance().consume(hash);

				int result=response.getReturn(Integer.class);
				if(result==2)
				   SwingUtils.showMessage(null, "Succeed returnning", "test");
				if(result==1)
				   SwingUtils.showError(null,"This book has not been borrowed", "test");
				if(result==0)
					SwingUtils.showError(null, "The ISBN is invalid", "test");
				}


		});
		btnComfirm.setBounds(76, 140, 113, 27);
		lblISBN.add(btnComfirm);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
	}
}
