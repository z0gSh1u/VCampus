package tech.zxuuu.server.main;

import java.util.Date;

import tech.zxuuu.util.OtherUtils;

public class UtilsApi {
	
	public static long getTrustedUnixTimeStamp() {
		return OtherUtils.getLocalTime().getTime();
	}

}
