package tech.zxuuu.server.teaching;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sun.xml.internal.bind.v2.model.core.ClassInfo;

import tech.zxuuu.dao.ITeacherMapper;
import tech.zxuuu.server.main.App;

public class CourseManagerSide {

	public static String getTeacherNameById(String academy, String idInAcademy) {
		String result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			ITeacherMapper teacherMapper = sqlSession.getMapper(ITeacherMapper.class);
			Map<String, String> map = new HashMap<>();
			map.put("academy", academy);
			map.put("idInAcademy", idInAcademy);
			result = teacherMapper.getTeacherNameById(map);			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// TODO finish this
	public static Boolean insertNewCourse(ClassInfo classInfo){
		String result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			ITeacherMapper teacherMapper = sqlSession.getMapper(ITeacherMapper.class);
			Map<String, String> map = new HashMap<>();
			result = teacherMapper.getTeacherNameById(map);			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
