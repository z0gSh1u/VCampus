package tech.zxuuu.client.messageQueue;

import java.util.HashMap;
import java.util.Map;

import tech.zxuuu.net.Response;

/**
 * 客户端请求消息队列（生产者-消费者模式）
 * 
 * @author z0gSh1u
 */
public class ResponseQueue {

	private static ResponseQueue responseQueue = new ResponseQueue();
	public static ResponseQueue getInstance() {
		return responseQueue;
	}
	
	private Map<String, Response> mp; // Map: Hash -> Response
	
	protected ResponseQueue() {
		mp = new HashMap<>();
	}
	
	public void produce(Response response) {
		mp.put(response.getHash(), response);
	}
	
	public Response consume(String hashCode) {
		Response response = mp.get(hashCode);
		mp.remove(hashCode);
		return response;
	}
	
	public boolean contain(String hash) {
		return mp.containsKey(hash);
	}
	
}
