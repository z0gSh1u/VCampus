package tech.zxuuu.net;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端到服务器的连接，出于类型安全而设置
 * 
 * @author z0gSh1u
 */
public class ConnectionToServer extends Connection {

	public ConnectionToServer(Socket socket) {
		super(socket);
	}
	
	@Override
	public void write(String content) {
		 System.out.println("半同步方法Connection.write被进入了");
		 synchronized (ConnectionToServer.class) {
			 System.out.println("同步代码块进入");
			 this.pWriter.write(content + "\n");
			 this.pWriter.flush();
		 }
	}
	
	@Override
	public synchronized String readLine() {
		//synchronized (ConnectionToServer.class) {
			try {
				return this.bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		//}
	}

}
