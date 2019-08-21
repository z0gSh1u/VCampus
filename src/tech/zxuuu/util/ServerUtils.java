package tech.zxuuu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.InetAddress;


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
	
}
