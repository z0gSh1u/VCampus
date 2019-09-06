package tech.zxuuu.client.library;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.javassist.compiler.ast.NewExpr;

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
import javax.swing.SwingUtilities;

import java.awt.Toolkit;

/**
 * 图书馆学生主页
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class LibraryStudentGUI extends JFrame {

	private JPanel contentPane;
	private Boolean click = true;

	/**
	 * Create the frame.
	 */
	public LibraryStudentGUI() {
		setTitle("图书馆 - VCampus");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LibraryStudentGUI.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);

		setVisible(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBorrow = new JButton("借书");
		btnBorrow.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/导入.png")));
		btnBorrow.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnBorrow.setBounds(524, 163, 172, 84);
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
		btnReturn.setBounds(524, 471, 172, 84);
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

		JButton btnRenew = new JButton("我的借阅");
		btnRenew.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/续签.png")));
		btnRenew.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnRenew.setBounds(525, 316, 171, 84);
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
		HotList.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, 300 * 5));

		JLabel lblLibraryIcon = new JLabel("");
		lblLibraryIcon.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/library.png")));
		lblLibraryIcon.setBounds(21, 10, 64, 64);
		contentPane.add(lblLibraryIcon);

		JLabel lblVcampus = new JLabel("图书馆 - VCampus");
		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		lblVcampus.setBounds(98, 21, 260, 43);
		contentPane.add(lblVcampus);

		JLabel lblHotBookIcon = new JLabel("热门书籍");
		lblHotBookIcon.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblHotBookIcon.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/火.png")));
		lblHotBookIcon.setBounds(189, 94, 148, 48);
		contentPane.add(lblHotBookIcon);

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
						list.get(i).getNumofborrowed());
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						hotBook.setBounds(0, 0, 445, 300);
						HotList.add(hotBook);
						hotBook.repaint();
						HotList.repaint();
					}
				});
			}
		}

		LibraryStudentGUI that = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

				that.setVisible(true);
			}
		});

	}
}
