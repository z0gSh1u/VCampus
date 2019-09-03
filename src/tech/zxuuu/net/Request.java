package tech.zxuuu.net;

import com.alibaba.fastjson.*;

/**
 * 请求类
 * 
 * @author z0gSh1u
 */

public class Request {

	private ConnectionToServer connectionToServer; // 到服务器的连接，依靠它来发送请求
	private ConnectionToClient connectionToClient; // 到客户端的连接，依靠它来发送响应，由底层的RequestListener设定
	private Session session; // 用户Session信息
	private String hash; // 请求-响应对哈希
	private String targetApi; // 请求目标接口
	private Object[] params; // 请求目标接口参数，注意，params.length应正好，且元素顺序应对应

	public Request() {}	
	public Request(ConnectionToServer connectionToServer, Session session,
			String targetApi, Object[] params) {
		super();
		this.connectionToServer = connectionToServer;
		this.connectionToClient = null;
		this.session = session;
		this.hash = null;
		this.targetApi = targetApi;
		this.params = params;
	}
	
	// 组装JSON发送，返回本次请求的Hash
	public String send() {
		// 在发送请求的前一时刻，Request对象的最终形态才确定，此时的Hash才有意义
		this.hash = String.valueOf(this.hashCode());
		String json = JSON.toJSONString(this);
		this.connectionToServer.write(json);

		return this.hash;
	}

	public ConnectionToServer getConnectionToServer() {
		return connectionToServer;
	}

	public void setConnectionToServer(ConnectionToServer connectionToServer) {
		this.connectionToServer = connectionToServer;
	}

	public ConnectionToClient getConnectionToClient() {
		return connectionToClient;
	}

	public void setConnectionToClient(ConnectionToClient connectionToClient) {
		this.connectionToClient = connectionToClient;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getTargetApi() {
		return targetApi;
	}

	public void setTargetApi(String targetApi) {
		this.targetApi = targetApi;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
