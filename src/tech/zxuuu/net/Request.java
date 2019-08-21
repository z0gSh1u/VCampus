package tech.zxuuu.net;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.*;

import tech.zxuuu.entity.Student;
import tech.zxuuu.entity.UserType;

/**
 * 请求类
 * 
 * @author z0gSh1u
 */

public class Request {

	private ConnectionToServer connToServer;
	private ConnectionToClient connToClient;
	private Session sess;
	private String hashCode;
	private String targetApi;
	private Object[] params;

	@Override
	public String toString() {
		return "Request [conn=" + connToServer + ", sess=" + sess + ", hashCode=" + hashCode + ", targetApi=" + targetApi
				+ ", params=" + Arrays.toString(params) + "]";
	}

	public Request(ConnectionToServer conn, Session sess, String targetApi, Object[] params) {
		super();
		this.connToServer = conn;
		this.connToClient = null;
		this.sess = sess;
		this.hashCode = String.valueOf(this.hashCode());
		this.targetApi = targetApi;
		this.params = params;
	}

	public ConnectionToClient getConnToClient() {
		return connToClient;
	}

	public void setConnToClient(ConnectionToClient connToClient) {
		this.connToClient = connToClient;
	}

	public Session getSession() {
		return sess;
	}

	public void setSession(Session sess) {
		this.sess = sess;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
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

	public ConnectionToServer getConnToServer() {
		return connToServer;
	}

	public void setConnToServer(ConnectionToServer conn) {
		this.connToServer = conn;
	}

	public Session getSess() {
		return sess;
	}

	public void setSess(Session sess) {
		this.sess = sess;
	}

	// 组装JSON发送，返回本次请求的Hash
	public String send() {
		
		this.hashCode = String.valueOf(this.hashCode());
		
		System.out.println("[REQ] " + hashCode);
		
		String json = JSON.toJSONString(this);
		
		System.out.println(connToServer);
		
		this.connToServer.write(json);
		
		return hashCode;
	}

}
