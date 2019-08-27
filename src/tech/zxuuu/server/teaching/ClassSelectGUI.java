package tech.zxuuu.server.teaching;

import java.util.List;

import javax.sound.sampled.LineListener;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.teaching.ClassInfo;
import tech.zxuuu.dao.IClassMapper;
import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.server.main.App;

public class ClassSelectGUI {
	
	public static List<ClassInfo> getClassInfo(Student a) {
		List<ClassInfo> result =null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassInfo(a);
			sqlSession.commit();
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

}
