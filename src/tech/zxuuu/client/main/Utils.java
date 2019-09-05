package tech.zxuuu.client.main;

import tech.zxuuu.util.ServerUtils;
import java.net.Socket;

import tech.zxuuu.net.*;

public class Utils {
	
	public static String getServerHost() {
		return ServerUtils.getServerHost();
	}
	
	public static int getMainPort() {
		return Integer.parseInt(ServerUtils.getMainPort());
	}
	
	public static int getChatPort() {
		return Integer.parseInt(ServerUtils.getChatPort());
	}
	
	// 只应该被调用一次，connectionToServer作为长连接，在客户端整个生命周期内流动
	public static ConnectionToServer formConnection() {
		ConnectionToServer connectionToServer = null;
		try {
			connectionToServer = new ConnectionToServer(new Socket(Utils.getServerHost(), Utils.getMainPort()));
		} catch (Exception e) {
			return null;
		}
		return connectionToServer;
	}
	
}
