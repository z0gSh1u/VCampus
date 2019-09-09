package tech.zxuuu.server.teaching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IClassMapper;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.server.main.App;

/**
 * 20190909版本更新后端，主要是考务管理
 * 
 * @author z0gSh1u
 */

public class Addons {

	public static String getStudentOfOneClass(String classId) {
		String result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getStudentOfOneClass(classId);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean updateScoreOfOneClass(String classId, String content) {
		Boolean result = null;
		SqlSession sqlSession = App.sqlSessionFactory.openSession();
		try {
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", classId);
			map.put("content", content);
			result = classMapper.updateScoreOfOneClass(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<String> getScoreList(String card, String raw) {
		List<String> result = new ArrayList<String>();
		String[] classIds = raw.split(",");
		for (int i = 0; i < classIds.length; i++) {
			String onerow = getStudentOfOneClass(classIds[i]);
			if (onerow == null || onerow.equals("")) {
				continue;
			}
			String[] onerows = onerow.split(",");
			for (int j = 0; j < onerows.length; j++) {
				if (onerows[j].substring(0, 9).equals(card)) {
					result.add(onerows[j].substring(9));
					break;
				}
			}
		}
		return result;
	}

}
