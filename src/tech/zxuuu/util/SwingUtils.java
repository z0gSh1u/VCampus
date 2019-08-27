package tech.zxuuu.util;

import javax.swing.*;

/**
 * Swing GUI设计实用工具
 * @author z0gSh1u
 */
public final class SwingUtils {

	// 检查文本框是否为空
	static public boolean isTxtEmpty(JTextField txt) {
		return txt.getText().trim().equals("");
	}
	
	// 弹出信息提示框
	static public void showMessage(JPanel parent, String msg, String title) {
		JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	// 弹出错误提示框
	static public void showError(JPanel parent, String msg, String title) {
		JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
}
