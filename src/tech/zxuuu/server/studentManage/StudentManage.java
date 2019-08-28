package tech.zxuuu.server.studentManage;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.bcel.internal.generic.RET;

import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.Student;
import tech.zxuuu.server.main.App;

public class StudentManage {

	// CURD
	
	public static Boolean insertStudent(Student student) {
		
		Boolean result = false;
		SqlSession sqlSession = null;
		
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			
			result = studentMapper.insertStudent(student);
			sqlSession.commit();
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
			return "Nocard";
		}
		int two = studentMapper.searchStudentByStudentNumber(studentnumber);
		if (two == 1) {
			return "Repeat";
		}
		Student baigei = new Student();
		baigei.setAcademy(academy);
		baigei.setCardNumber(cardnumber);
		baigei.setStudentNumber(studentnumber);
		studentMapper.switchStudent(baigei);
		sqlSession.commit();
	} catch (Exception e) {
		sqlSession.rollback();
		e.printStackTrace();
	}
	return "Ok";	
    }



}
