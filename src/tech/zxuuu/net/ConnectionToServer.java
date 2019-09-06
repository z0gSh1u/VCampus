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
		synchronized (ConnectionToServer.class) {
			this.pWriter.write(content + "\n");
			this.pWriter.flush();
		}
	}

	@Override
	public synchronized String readLine() {
		// readLine本身就是阻塞的，无需同步
		try {
			return this.bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
