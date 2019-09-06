package tech.zxuuu.server.opencourse;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IOpenCourseMapper;
import tech.zxuuu.entity.EmoticonInfo;
import tech.zxuuu.server.main.App;

/**
 * StuCourse界面对应后端
 * 
 * @author LongChen
 */
public class StuCourse {
	public static List<EmoticonInfo> getEmoticonList() {
		List<EmoticonInfo> list = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
			list = openCourseMapper.getEmoticonList();
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
