package tech.zxuuu.server.opencourse;

public interface ReceivePredicate {
	void callResponse(String str, ChatSocket chatSocket);
}
