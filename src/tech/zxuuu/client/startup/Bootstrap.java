package tech.zxuuu.client.startup;

import tech.zxuuu.util.SwingUtils;

import java.awt.EventQueue;

import tech.zxuuu.client.main.App;

/**
 * 客户端启动类
 * 
 * @author z0gSh1u
 */

public class Bootstrap {

	public static void main(String[] args) {

		System.out.println("Client started.");
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			SwingUtils.showError(null, "主题加载失败。", "错误");
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
