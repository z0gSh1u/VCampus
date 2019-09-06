package tech.zxuuu.server.opencourse;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.client.main.Utils;
import tech.zxuuu.entity.UserType;
import tech.zxuuu.server.main.App;

/**
 * 聊天连接管理中心
 * 
 * @author LongChen
 */
public class ChatManager extends Thread {
	private static ChatManager instance = null;

	public static ChatManager getInstance() {
		if (instance == null)
			instance = new ChatManager();
		return instance;
	}

	private ChatManager() {
		try {
			serverSocket = new ServerSocket(Utils.getChatPort());
		} catch (Exception e) {
			App.appendLog("[ChatManager]" + e.getMessage());
			return;
		}
		userLists = new HashMap<Integer, Vector<ChatSocket>>();
		userLists.clear();
		userLists.put(-1, new Vector<ChatSocket>());
	}

	private ServerSocket serverSocket;
	private Map<Integer, Vector<ChatSocket>> userLists;

	private void broadcast(String str, ChatSocket speaker) {
		String text = "";
		switch (speaker.getUserType()) {
		case STUDENT:
			text = "[学生]";
			break;
		case TEACHER:
			text = "<font color=\"Blue\">[老师]</font>";
			break;
		case MANAGER:
			text = "<font color=\"Red\">[管理员]</font>";
			break;
		}
		text += "【" + speaker.getName() + "】<br/>" + str + "<br/>";
		int key = speaker.getCourseId();
		for (ChatSocket user : userLists.get(key)) {
			user.write(text);
		}
	}

	private void receive(String str, ChatSocket speaker) {
		if (speaker.getCourseId() < 0 || speaker.getUserType() == null || speaker.getName().equals("")) {
			try {
				StringTokenizer st = new StringTokenizer(str, "\\");
				speaker.setCourseId(Integer.parseInt(st.nextToken()));
				speaker.setUserType(JSON.parseObject(st.nextToken(), UserType.class));
				speaker.setName(st.nextToken());
			} catch (Exception e) {
				App.appendLog("[ChatManager]未能正确设置新连接的配置\n[ChatManager]错误信息" + e.getMessage());
				throw e;
			}
			int key = speaker.getCourseId();
			if (!userLists.containsKey(key)) {
				userLists.put(key, new Vector<ChatSocket>());
			}
			userLists.get(key).add(speaker);
			userLists.get(-1).remove(speaker);
			App.appendLog("[ChatManager]新连接配置完成:");
		} else {
			App.appendLog("[ChatManager]即将广播信息:" + str);
			broadcast(str, speaker);
		}
	}

	@Override
	public void run() {
		try {
			App.appendLog("[ChatManager]开始等待连接");
			while (true) {
				userLists.get(-1).add(
						new ChatSocket(serverSocket.accept(), (String str, ChatSocket chatSocket) -> receive(str, chatSocket)));
				App.appendLog("[ChatManager]一个新的连接加入了");
			}
		} catch (Exception e) {
			App.appendLog("[ChatManager]" + e.getMessage());
		}
	}
}
