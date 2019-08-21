package tech.zxuuu.net;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 响应类
 * 
 * @author z0gSh1u
 */

public class Response {

	private ConnectionToClient conn; // 服务器到客户端的连接
	private String hashCode; // 与请求对应的哈希码
	private String fromApi; // 响应的Api
	private Object param; // 响应体（业务数据）

	public Response(ConnectionToClient conn, String hashCode, String fromApi, Object param) {
		super();
		this.conn = conn;
		this.hashCode = hashCode;
		this.fromApi = fromApi;
		this.param = param;
	}

	@Override
	public String toString() {
		return "Response [conn=" + conn + ", hashCode=" + hashCode + ", fromApi=" + fromApi + ", params="
				+ param + "]";
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getFromApi() {
		return fromApi;
	}

	public void setFromApi(String fromApi) {
		this.fromApi = fromApi;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public ConnectionToClient getConn() {
		return conn;
	}

	public void setConn(ConnectionToClient conn) {
		this.conn = conn;
	}
	
	public Object getReturn(Class<?> returnClazz) {
		if (returnClazz == String.class || returnClazz == Integer.class) {
			return this.getParam();
		}
		//		returnClazz.equals(String.class);
		return JSON.parseObject(this.getParam().toString(), returnClazz);
	}
	
	// 组装JSON发送，不返回
	public void send() {
		String json = JSON.toJSONString(this);
		this.conn.write(json);
		
		System.out.println("[RESP] " + json);
		
	}

}
