package tech.zxuuu.server.studentManage;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.Student;
import tech.zxuuu.server.main.App;

/**
 * 学生管理后端
 * 
 * @author Twileon
 */
public class StudentManage {

	public static String getNameByCardNumber(String cardNumber) {
		String result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.getNameByCardNumber(cardNumber);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean insertStudent(Student student) {
		Boolean result = false;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.insertStudent(student);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean deleteStudent(String cardnumber) {
		Boolean result = false;
		SqlSession sqlSession = null;
		int ret = 0;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			ret = studentMapper.deleteStudent(cardnumber);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return (ret == 0 ? false : true);
	}

	public static String switchStudent(String cardnumber, String academy, String studentnumber) {
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			int one = studentMapper.searchStudentByCardNumber(cardnumber);
			if (one == 0) {
				sqlSession.close();
				return "Nocard";
			}
			int two = studentMapper.searchStudentByStudentNumber(studentnumber);
			if (two == 1) {
				sqlSession.close();
				return "Repeat";
			}
			Student baigei = new Student();
			baigei.setAcademy(academy);
			baigei.setCardNumber(cardnumber);
			baigei.setStudentNumber(studentnumber);
			int rows = studentMapper.switchStudent(baigei);
			sqlSession.commit();
			sqlSession.close();
			if (rows == 0) {
				return "Nocard";
			} else {
				return "Ok";
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return "...";
	}

	public static List<Student> tableDisplay(String academy, String grade) {
		List<Student> result = null;
		SqlSession sqlSession = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("academy", academy);
		map.put("grade", grade);
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.tableDisplay(map);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean resetPassword(String cardNumber, String newPasswordMD5) {
		Boolean result = null;
		SqlSession sqlSession = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cardNumber", cardNumber);
		map.put("password", newPasswordMD5);
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.resetPassword(map);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
