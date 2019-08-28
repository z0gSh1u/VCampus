package tech.zxuuu.client.auth;

import tech.zxuuu.client.messageQueue.*;
import tech.zxuuu.entity.*;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.client.main.App;

public class AuthHelper {

	public static Student verifyStudent(String cardNumber, String password) {
		// 组织请求
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.studentLoginChecker",
				new Object[] { new Student(cardNumber, null, password, null) });
		String hash = req.send();
		// 等待响应
		ResponseUtils.blockAndWaitResponse(hash);
		// 消费响应
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(Student.class);
	}
	
	public static Teacher verifyTeacher(String cardNumber, String password) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.teacherLoginChecker",
				new Object[] { new Teacher(cardNumber, password, null, null) });
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(Teacher.class);
	}

	public static Manager verifyManager(String cardNumber, String password) {
		Request req = new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.managerLoginChecker",
				new Object[] { new Manager(cardNumber, password, null) });
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getReturn(Manager.class);
	}
	
}
