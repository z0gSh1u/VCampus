package tech.zxuuu.client.teaching.teacherSide;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mchange.v1.identicator.test.TestIdHashSet;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreDetailSetting extends JFrame {

	private JPanel contentPane;

	String[] head = { "学生一卡通", "学生", "给分" };
	private DefaultTableModel model;
	private JTable table;
	String[][] L2;
	String[] L1;
	boolean haventSave = false;

	public String getStudentNameByCardNumber(String card) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.getNameByCardNumber", new Object[] { card }).send())
				.getReturn(String.class);
	}

	public String getStudentOfOneClass(String classId) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.Addons.getStudentOfOneClass", new Object[] { classId }).send())
				.getReturn(String.class);
	}

	public Boolean updateScoreOfOneClass(String classId, String fullReturn) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.Addons.updateScoreOfOneClass", new Object[] { fullReturn }).send())
				.getReturn(Boolean.class);
	}

	public ScoreDetailSetting(String classId, String className) {
		JFrame that = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (haventSave) {
					int choose = JOptionPane.showConfirmDialog(null, "您还没有保存，确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
					if (choose == JOptionPane.YES_OPTION) {
						that.dispose();
					} else if (choose == JOptionPane.NO_OPTION) {
						// do nothing
					} else if (choose == JOptionPane.DEFAULT_OPTION) {
						// do nothing
					}
				} else {
					that.dispose();
				}
			}
		});
		setResizable(false);
		setTitle("课程给分 - VCampus");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ScoreDetailSetting.class.getResource("/resources/assets/icon/fav.png")));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 1028, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("当前操作课程：");
		lblNewLabel.setBounds(14, 13, 105, 18);
		contentPane.add(lblNewLabel);

		JLabel lblClassDisp = new JLabel("New label");
		lblClassDisp.setBounds(133, 13, 429, 18);
		contentPane.add(lblClassDisp);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 44, 982, 589);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnSave = new JButton("确认保存");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 组织content
				StringBuilder sb = new StringBuilder();
				if (model.getRowCount() == 0) {
					SwingUtils.showError(null, "无内容保存！", "提示");
					return;
				}
				for (int i = 0; i < L1.length; i++) {
					sb.append(L2[i][0]);
					sb.append(L2[i][2]);
					sb.append(',');
				}

				Boolean result = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.Addons.updateScoreOfOneClass", new Object[] { classId, sb.toString() }).send())
						.getReturn(Boolean.class);
				if (result) {
					SwingUtils.showMessage(null, "保存成功！", "提示");
					haventSave = false;
				} else {
					SwingUtils.showError(null, "保存失败", "提示");
				}

			}
		});
		btnSave.setIcon(new ImageIcon(ScoreDetailSetting.class.getResource("/resources/assets/icon/save.png")));
		btnSave.setBounds(867, 646, 129, 41);
		contentPane.add(btnSave);

		String rawStudentData = getStudentOfOneClass(classId);
		L1 = rawStudentData.split(",");
		L2 = new String[L1.length][3];

		if (rawStudentData == null || rawStudentData.length() == 0) {
			model = new DefaultTableModel(null, head) {
				@Override
				public boolean isCellEditable(int a, int b) {
					return false;
				}
			};
		} else {
			for (int i = 0; i < L1.length; i++) {
				L2[i][0] = L1[i].substring(0, 9);
				L2[i][1] = getStudentNameByCardNumber(L2[i][0]);
				L2[i][2] = L1[i].substring(9).equals("xxx") ? "——" : String.valueOf(Integer.parseInt(L1[i].substring(9)));
			}
			model = new DefaultTableModel(L2, head) {
				@Override
				public boolean isCellEditable(int r, int c) {
					return false;
				}
			};
		}

		table.setModel(model);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
					String rawInput = SwingUtils.popInput("请输入 " + L2[row][1] + " 的给分（不给分可放空）");
					if (rawInput.trim().equals("")) {
						L2[row][2] = "xxx";
						model.setValueAt("——", row, 2);
						haventSave = true;
					} else {
						if (!SwingUtils.isPureDigits(rawInput)) {
							SwingUtils.showError(null, "非法分数输入！", "错误");
						} else {
							int parse = Integer.parseInt(rawInput);
							if (parse > 100 || parse < 0) {
								SwingUtils.showError(null, "非法分数输入！", "错误");
							} else {
								L2[row][2] = rawInput;
								model.setValueAt(rawInput, row, 2);
								haventSave = true;
							}
						}
					}
				}
			}
		});

		lblClassDisp.setText(className);

	}

}
