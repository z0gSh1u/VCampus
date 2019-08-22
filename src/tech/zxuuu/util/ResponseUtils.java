package tech.zxuuu.util;

import tech.zxuuu.client.messageQueue.ResponseQueue;

public final class ResponseUtils {

	public final static void blockAndWaitResponse(String hash) {
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
		}
	}
	
}
