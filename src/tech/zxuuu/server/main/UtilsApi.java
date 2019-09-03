package tech.zxuuu.server.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.util.OtherUtils;

public class UtilsApi {

	public static long getTrustedUnixTimeStamp() {
		return OtherUtils.getLocalTime().getTime();
	}

	public static Boolean chargeCardBalance(String cardNumber, Integer howmuch) {
		Boolean result = null;
		SqlSession sqlSession = null;

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("cardNumber", cardNumber);
		map.put("money", howmuch);

		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.chargeCard(map);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

}
