package tech.zxuuu.server.opencourse;

import java.io.IOException;
import java.net.Socket;

import tech.zxuuu.entity.UserType;
import tech.zxuuu.net.Connection;

/**
 * 聊天用多功能Socket
 * 
 * @author LongChen
 */
public class ChatSocket extends Connection implements Runnable {
	private ReceivePredicate receiveResponse;
	private UserType userType;
	private String name;
	private int courseId;

	public ChatSocket(Socket socket, ReceivePredicate receiveResponse) {
		super(socket);
		this.receiveResponse = receiveResponse;
		this.courseId = -1;
		this.userType = null;
		this.name = "";
		new Thread(this).start();
	}

	@Override
	public void run() {
		String str = "";
		while ((str = this.readLine()) != null) {
			receiveResponse.callResponse(str, this);
		}
	}

	@Override
	public void write(String content) {
		synchronized (ChatSocket.class) {
			this.pWriter.write(content + "\n");
			this.pWriter.flush();
		}
	}

	@Override
	public String readLine() {
		try {
			return this.bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
