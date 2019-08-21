package tech.zxuuu.client.auth;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.net.ConnectionToServer;
import tech.zxuuu.net.Response;

public class Listener extends Thread {

	private ConnectionToServer conn;
	
	public Listener(ConnectionToServer conn) {
		this.conn = conn;
	}
	
	@Override 
	public void run() {
		String line;
		while ((line = conn.readLine()) != null) {
			Response resp = JSON.parseObject(line, Response.class);
			AuthGUI.responseQueue.produce(resp);
		}
	}
	
}
