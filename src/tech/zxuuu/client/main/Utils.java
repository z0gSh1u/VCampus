package tech.zxuuu.client.main;

import tech.zxuuu.util.ServerUtils;
import tech.zxuuu.util.SwingUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import tech.zxuuu.net.*;

public class Utils {
	
	public static String getServerHost() {
		return ServerUtils.getServerHost();
	}
	
	public static int getMainPort() {
		return Integer.parseInt(ServerUtils.getMainPort());
	}
	
	// 只应该被调用一次，conn作为长连接，在客户端整个生命周期内流动
	public static ConnectionToServer formConnection() {
		ConnectionToServer conn = null;
		try {
			conn = new ConnectionToServer(new Socket(Utils.getServerHost(), Utils.getMainPort()));
		} catch (Exception e) {
			return null;
		}
		return conn;
	}
	
}
