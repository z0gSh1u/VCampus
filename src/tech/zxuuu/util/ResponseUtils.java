package tech.zxuuu.util;

import java.util.Date;

import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Response;

public final class ResponseUtils {

	@Deprecated 
	/**
	 * @see `getResponseByHash` might be better
	 */
	public final static void blockAndWaitResponse(String hash) {
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
		}
	}
	
	public final static Response getResponseByHash(String hash) {
		// support timeout
		long shouldEnd = new Date().getTime() + 10000;
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
			if (new Date().getTime() >= shouldEnd) {
				return null;
			}
		}
		return ResponseQueue.getInstance().consume(hash);
	}
	
}
