package tech.zxuuu.client.messageQueue;

import java.util.HashMap;
import java.util.Map;

import tech.zxuuu.net.Response;

// 基于生产者-消费者模型的响应消息队列（单例）
public class ResponseQueue {

	private static ResponseQueue responseQueue = new ResponseQueue();
	public static ResponseQueue getInstance() {
		return responseQueue;
	}
	
	private Map<String, Response> mp;
	
	protected ResponseQueue() {
		mp = new HashMap<>();
	}
	
	public void produce(Response response) {
		System.out.println("One Response is produced. Hash=" + response.getHashCode());
		mp.put(response.getHashCode(), response);
	}
	
	public Response consume(String hashCode) {
		System.out.println("One Response is consumed.");
		Response response = mp.get(hashCode);
		mp.remove(hashCode);
		return response;
	}
	
	public boolean contain(String hashCode) {
		return mp.containsKey(hashCode);
	}
	
}
