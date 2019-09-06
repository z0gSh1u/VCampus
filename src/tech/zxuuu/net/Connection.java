package tech.zxuuu.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 连接类（数据传输隧道）
 * 
 * @author z0gSh1u
 */

public abstract class Connection {

	protected Socket socket;
	protected PrintWriter pWriter;
	protected BufferedReader bReader;
	
	
	// 初始化连接，并初始化相关流读写器
	public Connection(Socket socket) {
		super();
		this.socket = socket;
		
		if (this.socket == null || !this.socket.isConnected()) {
			return;
		}
		try {
			this.pWriter = new PrintWriter
			  (new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
			this.bReader = new BufferedReader
	      (new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 为了加不同的锁，ConnectionToClient和ConnectionToServer应该自行实现读写方法
	// 向Socket写一段数据
	public abstract void write(String content);
	// 从Socket读一段数据
	public abstract String readLine();
	
	// 销毁连接
	public void destory() {
		try {
			pWriter.close();
			pWriter = null;
			bReader.close();
			bReader = null;
			socket.close();
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Connection [socket=" + socket + ", pWriter=" + pWriter + ", bReader=" + bReader + "]";
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getpWriter() {
		return pWriter;
	}

	public void setpWriter(PrintWriter pWriter) {
		this.pWriter = pWriter;
	}

	public BufferedReader getbReader() {
		return bReader;
	}

	public void setbReader(BufferedReader bReader) {
		this.bReader = bReader;
	}

}
