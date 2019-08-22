package tech.zxuuu.server.messageQueue;

import java.util.Vector;
import tech.zxuuu.net.Request;

/**
 * 服务器端请求消息队列（生产者-消费者模式）
 * 
 * @author z0gSh1u
 */
public class RequestQueue {

	private static RequestQueue requestQueue = new RequestQueue();

	public static RequestQueue getInstance() {
		return requestQueue;
	}

	private Vector<Request> vec;

	protected RequestQueue() {
		vec = new Vector<>();
	}

	public void produce(Request request) {
		vec.add(request);
	}

	public Request consume() {
		Request request = vec.get(0);
		vec.remove(0);
		return request;
	}

	public boolean hasNext() {
		return vec.size() > 0;
	}

}
