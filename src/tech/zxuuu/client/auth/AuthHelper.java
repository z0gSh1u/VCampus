package tech.zxuuu.client.auth;

import tech.zxuuu.entity.*;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.main.App;

/**
 * 登陆界面前端请求相关代码
 * 
 * @author z0gSh1u
 */
public class AuthHelper {

	public static Student verifyStudent(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.studentLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Student.class);
	}

	public static Teacher verifyTeacher(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.teacherLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Teacher.class);
	}

	public static Manager verifyManager(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.zxuuu.server.auth.Auth.managerLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Manager.class);
	}

}
