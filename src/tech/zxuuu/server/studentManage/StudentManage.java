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
	

public static Boolean switchStudent(String cardnumber,String academy) {
	

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

}
