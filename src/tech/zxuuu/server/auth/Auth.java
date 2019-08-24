package tech.zxuuu.server.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
	public static Boolean verifyTeacher(Teacher teacher) {
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
