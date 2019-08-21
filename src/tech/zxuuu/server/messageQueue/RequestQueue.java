package tech.zxuuu.server.messageQueue;

import java.util.Vector;
import tech.zxuuu.net.Request;

//基于生产者-消费者模型的请求消息队列（单例）
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
		System.out.println("One Request is produced.");
		vec.add(request);
	}

	public Request consume() {
		System.out.println("One Request is consumed.");
		Request request = vec.get(0);
		vec.remove(0);
		return request;
	}

	public boolean hasNext() {
		return vec.size() > 0;
	}

}
