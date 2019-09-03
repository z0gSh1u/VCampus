package tech.zxuuu.util;

import java.util.Date;

import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Response;

/**
 * 让请求-响应更好用的工具函数
 * 
 * @author z0gSh1u
 */

public final class ResponseUtils {

	@Deprecated
	/**
	 * 阻塞并等待响应
	 * @see `getResponseByHash` might be better
	 */
	public final static void blockAndWaitResponse(String hash) {
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
		}
	}

	/**
	 * 阻塞并获取具体响应，支持超时中断
	 */
	public final static Response getResponseByHash(String hash) {
		// support timeout
		long shouldEnd = new Date().getTime() + 10000;
		while (!ResponseQueue.getInstance().contain(hash)) {
			if (ResponseQueue.getInstance().contain(hash)) {
				break;
			}
			if (new Date().getTime() >= shouldEnd) {
				// TODO: uncomment this when release
				// SwingUtils.showError(null, "请求超时！Hash=" + hash, "错误");
				return null;
			}
		}
		return ResponseQueue.getInstance().consume(hash);
	}

}
