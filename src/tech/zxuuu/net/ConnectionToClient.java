package tech.zxuuu.net;

import java.io.IOException;
import java.net.Socket;

/**
 * 服务器到客户端的连接，出于类型安全而设置
 * 
 * @author z0gSh1u
 */
public class ConnectionToClient extends Connection {

	public ConnectionToClient(Socket socket) {
		super(socket);
	}

	public void write(String content) {
		System.out.println("半同步方法Connection.write被进入了");
		synchronized (ConnectionToClient.class) {
			System.out.println("同步代码块进入");
			this.pWriter.write(content + "\n");
			this.pWriter.flush();
		}
	}

	public synchronized String readLine() {
		//synchronized (ConnectionToClient.class) {
			try {
				return this.bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		//}
	}

}
