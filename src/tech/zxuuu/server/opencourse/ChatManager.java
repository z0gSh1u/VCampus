package tech.zxuuu.server.opencourse;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.client.main.Utils;
import tech.zxuuu.dao.IOpenCourseMapper;
import tech.zxuuu.entity.UserType;
import tech.zxuuu.server.main.App;

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
			System.out.println(e.getMessage());
			return;
		}
		userLists = new HashMap<Integer, Vector<ChatSocket>>();
		userLists.clear();
		userLists.put(-1, new Vector<ChatSocket>());
	}

	private ServerSocket serverSocket;
	private Map<Integer, Vector<ChatSocket>> userLists;

	/*
	private String toEmoticon(String str) {
		int curPos = str.indexOf("\\");
		while (curPos > -1) {
			int endPos = str.indexOf("/", curPos);
			if (endPos == -1)
				break;
			String name = str.substring(curPos + 1, endPos);
			String emo = null;
			try {
				SqlSession sqlSession = App.sqlSessionFactory.openSession();
				IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
				emo = openCourseMapper.getEmoticon(name);
				sqlSession.commit();
			} catch (Exception e) {
				App.appendLog("[ChatManager]在表情转换时发生错误：" + e.getMessage());
				System.out.println(e.getMessage());
				emo = "";
			}

			if (emo != null) {
				str = str.substring(0, curPos) + emo + str.substring(endPos + 1);
				System.out.println(str);
			}
			curPos = str.indexOf("\\", curPos+1);
		}
		return str;
	}
	*/

	private void broadcast(String str, ChatSocket speaker) {
		String text = "";
		//str = toEmoticon(str);
		switch (speaker.getUserType()) {
		case STUDENT:
			text = "[学生]";
			break;
		case TEACHER:
			text = "[老师]";
			break;
		case MANAGER:
			text = "[管理员]";
			break;
		}
		text += "【" + speaker.getName() + "】<br/>" + str + "<br/>";
		int key = speaker.getCourseId();
		for (ChatSocket user : userLists.get(key)) {
			// if(!user.equals(speaker)) {
			user.write(text);
			// }
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
			System.out.println(e.getMessage());
		}
	}
}
