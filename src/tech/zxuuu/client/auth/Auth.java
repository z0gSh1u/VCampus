package tech.zxuuu.client.auth;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.client.messageQueue.*;

import tech.zxuuu.entity.*;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.server.main.App;

public class Auth {

	private String username, password;
	private UserType type;
	
	public Auth(String username, String password, UserType type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public Boolean verifyUser() {

		//												conn,					sess,	api,																				 params
		Request req = new Request(AuthGUI.conn, null, "tech.zxuuu.server.auth.Auth.verifyStudent", new Object[] {
				new Student(username, password)
		});

		String hash = req.send();

		// 等待响应
		System.out.println("[WAIT] "+hash);
		//while (!ResponseQueue.getInstance().contain(hash)) {}
		while (true) {
			System.out.println("waiting...");
			if (ResponseQueue.getInstance().contain(hash)) {
				Response resp = ResponseQueue.getInstance().consume(hash);
				System.out.println(resp);
				System.out.println(resp.getParam());
				return (Boolean) resp.getReturn(Boolean.class);
			}
		}
		
	
	}
	
}
