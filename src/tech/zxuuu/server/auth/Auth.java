package tech.zxuuu.server.auth;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IManagerMapper;
import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.dao.ITeacherMapper;
import tech.zxuuu.entity.Manager;
import tech.zxuuu.entity.ManagerType;
import tech.zxuuu.entity.Student;
import tech.zxuuu.entity.Teacher;
import tech.zxuuu.server.main.*;

public class Auth {

	// 学生登录后端接口
	public static Student studentLoginChecker(Student student) {
		Student result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			Boolean verifyResult = studentMapper.verifyStudent(student);
			if (!verifyResult) {
				return null;
			}
			result = studentMapper.getStudentDetailByCardNumber(student.getCardNumber());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 教师登陆后端接口
	public static Teacher teacherLoginChecker(Teacher teacher) {
		Teacher result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			ITeacherMapper teacherMapper = sqlSession.getMapper(ITeacherMapper.class);
			Boolean verifyResult = teacherMapper.verifyTeacher(teacher);
			if (!verifyResult) {
				return null;
			}
			result = teacherMapper.getTeacherDetailByCardNumber(teacher.getCardNumber());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 管理员登陆后端接口
	public static Manager managerLoginChecker(Manager manager) {
		Manager result = null;
		
		System.out.println("后端接到manager=" + manager.toString());
		
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IManagerMapper managerMapper = sqlSession.getMapper(IManagerMapper.class);
			Boolean verifyResult = managerMapper.verifyManager(manager);
			if (!verifyResult) {
				return null;
			}
			result = managerMapper.getManagerDetailByCardNumber(manager.getCardNumber());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
