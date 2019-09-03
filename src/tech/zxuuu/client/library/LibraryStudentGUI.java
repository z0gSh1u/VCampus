package tech.zxuuu.client.library;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.jna.platform.win32.WinDef.SCODE;

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
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * 图书馆学生主页
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class LibraryStudentGUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private Boolean click = true;

	/**
	 * Create the frame.
	 */
	public LibraryStudentGUI() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LibraryStudentGUI.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBorrow = new JButton("借书");
		btnBorrow.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/导入.png")));
		btnBorrow.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnBorrow.setBounds(536, 152, 172, 84);
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryBook lq = new QueryBook();
				lq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lq.setModal(true);
				lq.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBorrow);

		JButton btnReturn = new JButton("还书");
		btnReturn.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/导出.png")));
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnReturn.setBounds(536, 460, 172, 84);
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

		JButton btnRenew = new JButton("我的借阅");
		btnRenew.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/续签.png")));
		btnRenew.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnRenew.setBounds(537, 305, 171, 84);
		btnRenew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyBorrowGUI renew = new MyBorrowGUI();
				renew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				renew.setVisible(true);
			}
		});
		contentPane.add(btnRenew);

		JPanel HotList = new JPanel();
		HotList.setLayout(new GridLayout(0, 1));

		JScrollPane scrollPane = new JScrollPane(HotList);
		scrollPane.setBounds(21, 152, 462, 403);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(HotList);
		HotList.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight() * 5));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/library.png")));
		lblNewLabel.setBounds(21, 10, 64, 64);
		contentPane.add(lblNewLabel);

		JLabel lblVcampus = new JLabel("图书馆 - VCampus");
		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		lblVcampus.setBounds(98, 21, 260, 43);
		contentPane.add(lblVcampus);

		JLabel lblNewLabel_1 = new JLabel("热门书籍");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel_1.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/火.png")));
		lblNewLabel_1.setBounds(189, 94, 148, 48);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/picture/wddnm.png")));
		lblNewLabel_2.setBounds(472, -3, 270, 147);
		contentPane.add(lblNewLabel_2);

		List<Book> list = null;
		list = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, App.session,
				"tech.zxuuu.server.library.BookServer.searchHotBook", new Object[] {}).send()).getListReturn(Book.class);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JPanel hotBook = new HotBook(list.get(i).getPictureURL(), list.get(i).getTitle(), list.get(i).getAuthor(),
						list.get(i).getNumofborrowed()); // hotBook 是个块
				HotList.add(hotBook);
			}
		}

	}
}
