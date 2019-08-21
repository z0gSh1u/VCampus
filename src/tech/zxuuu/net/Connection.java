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

	private Socket socket;
	private PrintWriter pWriter;
	private BufferedReader bReader;

	// 初始化连接，并提供流读写器
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
	
	// 同步方法，避免写入冲突
	// public synchronized void write(String content) {
	 public void write(String content) {
		System.out.println("writing || " + content);
		
		this.pWriter.write(content + "\n");
		this.pWriter.flush();
	}
	
	// 同步方法，避免读取冲突
	public synchronized String readLine() {
		try {
			return this.bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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

}
