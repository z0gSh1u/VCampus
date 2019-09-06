package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.teaching.teacherSide.ScheduleTablePane;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class AppTeacher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppTeacher frame = new AppTeacher();
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
	public AppTeacher() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppTeacher.class.getResource("/resources/assets/icon/fav.png")));
		setTitle("教师端 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 791);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppTeacher.class.getResource("/resources/assets/icon/teacher.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("教师主页 - VCampus");
		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		lblVcampus.setBounds(92, 26, 306, 43);
		contentPane.add(lblVcampus);

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setBounds(450, 43, 434, 18);
		contentPane.add(lblWelcome);

		lblWelcome.setText(
				"欢迎您， " + App.session.getTeacher().getName() + " 老师！您的一卡通号：" + App.session.getTeacher().getCardNumber());
		
		JPanel panel = new ScheduleTablePane();
		panel.setBounds(14, 103, 888, 618);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("排课表");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));

		lblNewLabel.setBounds(450, 76, 54, 24);

		contentPane.add(lblNewLabel);
	}

}
