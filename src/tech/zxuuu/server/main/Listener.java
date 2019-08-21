package tech.zxuuu.server.main;

import java.net.Socket;
import java.util.Properties;

import tech.zxuuu.net.ConnectionToClient;
import tech.zxuuu.net.Request;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;

import com.alibaba.fastjson.*;

/**
 * 服务器端口监听器
 * 
 * @author c1119
 */

public class Listener extends Thread {

	private int port;
	private ServerSocket serverSocket;

	public Listener() {
		Properties prop = new Properties();
		InputStream ins = this.getClass().getResourceAsStream("/resources/server.properties");
		try {
			prop.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.port = Integer.parseInt(prop.getProperty("mainport"));
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(this.port);
			while (true) {
				// 创建Socket
				Socket socket = serverSocket.accept();
				if (socket != null) {
					ConnectionToClient conn = new ConnectionToClient(socket);
					// 建立一个线程专门处理这个用户的请求
					new Thread() {
						
						@Override
						public void run() {
							String line;
							while ((line = conn.readLine()) != null) {
								// 一个line就是一个JSON String
								// 反序列化，获得请求
								
								// System.out.println(line);
								// hash code is right until here
								
								Request req = JSON.parseObject(line, Request.class);
								
								// TODO: 为什么需要人工重设hashCode？
								req.setHashCode(JSON.parseObject(line).getString("hashCode"));
								
								System.out.println("When finished parsing, hash="+req.getHashCode());
								
								// 设置响应回送位置
								req.setConnToClient(conn);
								// 将请求放入消息队列，等待消费
								App.requestQueue.produce(req);
								
							}
						}

					}.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}