package tech.zxuuu.server.auth;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.Student;
import tech.zxuuu.server.main.*;

public class Auth {

	// 学生登录接口
	public static Boolean verifyStudent(Student student) {
		
		Boolean result = false;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.verifyStudent(student);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static String getPassword(String username) {
		String res = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			
			res = studentMapper.getPasswordByUsername(username);
			
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return res;
	}
	
}
