package tech.zxuuu.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 服务器相关实用工具类
 * 
 * @author z0gSh1u
 */
public final class ServerUtils {

	final static String CONFIGPATH = "/resources/server.properties";

	public static String getServerHost() {
		Properties prop = new Properties();
		try {
			prop.load(ServerUtils.class.getResourceAsStream(CONFIGPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty("serverhost");
	}

	public static String getMainPort() {
		Properties prop = new Properties();
		try {
			prop.load(ServerUtils.class.getResourceAsStream(CONFIGPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty("mainport");
	}

	public static String getChatPort() {
		Properties prop = new Properties();
		try {
			prop.load(ServerUtils.class.getResourceAsStream(CONFIGPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty("chatport");
	}
}
