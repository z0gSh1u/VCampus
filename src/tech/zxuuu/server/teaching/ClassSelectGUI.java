package tech.zxuuu.server.teaching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IClassMapper;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.server.main.App;

/**
 * 学生选课相关后端
 * 
 * @author 王志华
 */
public class ClassSelectGUI {

	public static List<ClassInfo> getClassInfo(String academy) {
		List<ClassInfo> result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassInfo(academy);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean takeClass(Student student, String newClassId) {
		SqlSession sqlSession = App.sqlSessionFactory.openSession();
		Boolean one = null, two = null;
		try {
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			one = classMapper.takeClass(student);
			// add on to support exam system
			String temp = classMapper.getStudentOfOneClass(newClassId);
			temp += student.getCardNumber();
			temp += "xxx,";
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", newClassId);
			map.put("content", temp);
			two = classMapper.updateScoreOfOneClass(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return Boolean.logicalAnd(one, two);
	}

	public static Boolean takeClassInv(Student student, String dropClassId) {
		SqlSession sqlSession = App.sqlSessionFactory.openSession();
		Boolean one = null, two = null;
		try {
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			one = classMapper.takeClass(student);
			// add on to support exam system
			String temp = classMapper.getStudentOfOneClass(dropClassId);
			String[] sp = temp.split(",");
			String writeBack = "";
			for (String string : sp) {
				if (string.indexOf(student.getCardNumber()) != -1) {
					continue;
				}
				writeBack += string;
				writeBack += ",";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", dropClassId);
			map.put("content", writeBack);
			two = classMapper.updateScoreOfOneClass(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return Boolean.logicalAnd(one, two);
	}

	public static String getClassSelection(Student student) {
		String result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassSelection(student);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static ClassInfo getOneClass(String ID) {
		ClassInfo result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getOneClass(ID);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<ClassInfo> getClassOfOneTeacher(String name) {
		List<ClassInfo> result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassOfOneTeacher(name);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
