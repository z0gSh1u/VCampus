package tech.zxuuu.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.server.main.App;

/**
 * 服务器端口监听器（请求监听器）
 * 
 * @author z0gSh1u
 */
public class RequestListener extends Thread {

	private int port;
	private ServerSocket serverSocket;

	public RequestListener() {
	}

	public RequestListener(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(this.port);
			// 处理连接请求
			while (true) {
				Socket socket = serverSocket.accept();
				if (socket != null) {
					ConnectionToClient connectionToThisClient = new ConnectionToClient(socket);
					// 建立一个线程专门处理这个用户的请求（持久化连接）
					new Thread() {
						@Override
						public void run() {
							String line;
							while ((line = connectionToThisClient.readLine()) != null) {
								// 一个line就是一段JSON
								Request req = JSON.parseObject(line, Request.class);
								// 设置响应回送位置
								req.setConnectionToClient(connectionToThisClient);
								// 将请求放入消息队列，等待消费
								App.requestQueue.produce(req);
								System.out.println("服务器端请求已收到，完成生产");
							}
						}
					}.start();
				}
			}
		} catch (SocketException se) {
			System.err.println("某个Socket连接被Reset了。");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
