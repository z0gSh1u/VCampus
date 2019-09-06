package tech.zxuuu.server.opencourse;

/**
 * 黑科技
 * 
 * @author LongChen
 */
public interface ReceivePredicate {
	void callResponse(String str, ChatSocket chatSocket);
}
