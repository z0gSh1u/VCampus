package tech.zxuuu.server.teaching;

import java.util.List;

import javax.sound.sampled.LineListener;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.dao.IClassMapper;
import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.server.main.App;

public class ClassSelectGUI {
	
	public static List<ClassInfo> getClassInfo() {
		List<ClassInfo> result =null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassInfo();
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	public static Boolean takeClass(Student student) {
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			classMapper.takeClass(student);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return true;
	}
	
	public static String getClassSelection(Student student) {
		String result=null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result=classMapper.getClassSelection(student);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	public static ClassInfo getOneClass(String ID) {
		ClassInfo result =null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getOneClass(ID);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<ClassInfo> getClassOfOneTeacher(String name) {
		List<ClassInfo> result =null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassOfOneTeacher(name);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
}
