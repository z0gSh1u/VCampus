package tech.zxuuu.server.auth;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.Student;
import tech.zxuuu.entity.Teacher;
import tech.zxuuu.server.main.*;

public class Auth {

	// 学生登录后端接口
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
	
	// 教师登陆后端接口
	public static Teacher verifyTeacher(Teacher teacher) {
		Boolean result = false;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
//			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
//			result = studentMapper.verifyStudent(student);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	

}
