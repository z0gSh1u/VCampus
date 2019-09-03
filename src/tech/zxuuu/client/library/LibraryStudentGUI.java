package tech.zxuuu.client.library;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.entity.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LibraryStudentGUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private Boolean click = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LibraryStudentGUI frame = new LibraryStudentGUI();
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
	public LibraryStudentGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBorrow = new JButton("借书");
		btnBorrow.setBounds(35, 70, 113, 27);
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryBook lq = new QueryBook();
				lq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lq.setModal(true);
				lq.setVisible(true);// 打开另一个窗口
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBorrow);

		JButton btnReturn = new JButton("还书");
		btnReturn.setBounds(35, 162, 113, 27);
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReturnBook re = new ReturnBook();
				re.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				re.setModal(true);
				re.setVisible(true);
			}
		});
		contentPane.add(btnReturn);

		String[] popularBook = { "isbn", "title", "author", "number" };
		model = new DefaultTableModel(null, popularBook);

		JButton btnRenew = new JButton("续借");
		btnRenew.setBounds(35, 245, 113, 27);
		btnRenew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RenewBook renew = new RenewBook();
				renew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				renew.setVisible(true);
			}
		});
		contentPane.add(btnRenew);

		JPanel HotList = new JPanel();
		HotList.setLayout(new GridLayout(0, 1));

		JScrollPane scrollPane = new JScrollPane(HotList);
		scrollPane.setBounds(162, 77, 462, 452);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(HotList);
		HotList.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight() * 5));

		Request request = new Request(App.connectionToServer, App.session,
				"tech.zxuuu.server.library.BookServer.searchHotBook", new Object[] {});
		String hash = request.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response response = ResponseQueue.getInstance().consume(hash);
		List<Book> list = new ArrayList<>();
		list = response.getListReturn(Book.class);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JPanel hotBook = new HotBook(list.get(i).getPictureURL(), list.get(i).getTitle(), list.get(i).getAuthor(),
						list.get(i).getNumofborrowed());// hotBook 是个块
				HotList.add(hotBook);
			}
		}

	}
}
