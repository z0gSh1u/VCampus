package tech.zxuuu.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import tech.zxuuu.util.JSONUtils;

/**
 * 响应类
 * 
 * @author z0gSh1u
 */

public class Response {

	private ConnectionToClient connectionToClient; // 服务器到客户端的连接
	private String hash; // 与请求对应的哈希码
	private String fromApi; // 响应的Api
	private Object param; // 响应内容（业务数据）

	public Response() {
	}

	public Response(ConnectionToClient connectionToClient, String hash, String fromApi, Object param) {
		super();
		this.connectionToClient = connectionToClient;
		this.hash = hash;
		this.fromApi = fromApi;
		this.param = param;
	}

	// 获取单体业务数据
	public <T> T getReturn(Class<T> clazz) {
		
		// 该方法不处理List和Map型Clazz
		if (clazz == List.class || clazz == Map.class) {
			System.err.println("[Response.getReturn] List and/or Map class type can't be used in getReturn(Class<T> clazz).");
		}
		
		if (this.param == null) {
			return null;
		}
		
		if (JSONUtils.isBasicClass(clazz)) {
			// 对于基本类型，不解析
			return clazz.cast(this.param);
		} else {
			// 对于自定义类，嵌套解析
			JSONObject temp = (JSONObject) this.param;
			return JSON.parseObject(temp.toJSONString(), clazz);
		}
	
	}
	
	// 获取数组型业务数据
	public <T> List<T> getListReturn(Class<T> elementClazz) {
		
		if (this.param == null) {
			return null;
		}
		
		JSONArray tempParam = (JSONArray) this.param;
		
		System.out.println(tempParam.toJSONString());
		
		List<Object> unparsedList = JSON.parseArray(tempParam.toJSONString());
		List<T> processedList = new ArrayList<T>();
		
		// 对于基本类型，不解析
		if (JSONUtils.isBasicClass(elementClazz)) {
			for (int i = 0; i < unparsedList.size(); i++) {
				processedList.add(elementClazz.cast(unparsedList.get(i)));
			}
		} else {
			// 处理嵌套类型
			for (int i = 0; i < unparsedList.size(); i++) {
				JSONObject jsonObject = (JSONObject) unparsedList.get(i);
				processedList.add((T) JSON.parseObject(jsonObject.toJSONString(), elementClazz));
			}
		}
		
		return processedList;
		
	}
	
	// 获取Map型业务数据
	public <T> Map<String, T> getMapReturn(Class<T> valueClazz) {
		
		if (this.param == null) {
			return null;
		}
		
		JSONObject tempParam = (JSONObject) this.param;
		Map<String, Object> unparsedMap = JSON.parseObject(tempParam.toJSONString());
		Map<String, T> processedMap = new HashMap<>();
		
		// 对于基本类型，不解析
		if (JSONUtils.isBasicClass(valueClazz)) {
			for (String key : unparsedMap.keySet()) {
				processedMap.put(key, valueClazz.cast(unparsedMap.get(key)));
			}
		} else {
			// 处理嵌套类型
			for (String key : unparsedMap.keySet()) {
				JSONObject jsonObject = (JSONObject) unparsedMap.get(key);
				processedMap.put(key, (T) JSON.parseObject(jsonObject.toJSONString(), valueClazz));
			}
		}
		
		return processedMap;
		
	}
	
	
	// 组装JSON发送到客户端
	public void send() {
		String json = JSON.toJSONString(this);
		this.connectionToClient.write(json);
		System.out.println("服务器端一个响应被组织并写到连接了");
	}

	public ConnectionToClient getConnectionToClient() {
		return connectionToClient;
	}

	public void setConnectionToClient(ConnectionToClient connectionToClient) {
		this.connectionToClient = connectionToClient;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
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

	@Override
	public String toString() {
		return "Response [connectionToClient=" + connectionToClient + ", hash=" + hash + ", fromApi=" + fromApi + ", param="
				+ param + "]";
	}

}
