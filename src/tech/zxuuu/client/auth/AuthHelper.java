package tech.zxuuu.client.auth;

import tech.zxuuu.client.messageQueue.*;
import tech.zxuuu.entity.*;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.client.main.App;

public class AuthHelper {

	public static Boolean verifyStudent(String username, String password) {
		// 组织请求
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.verifyStudent",
				new Object[] { new Student(username, password) });
		String hash = req.send();
		// 等待响应
		ResponseUtils.blockAndWaitResponse(hash);
		// 消费响应
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(Boolean.class);
	}
	
}
