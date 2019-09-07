package tech.zxuuu.server.opencourse;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IOpenCourseMapper;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.server.main.App;

/**
 * 公开课管理员后端
 * 
 * @author z0gSh1u
 * @author 王志华
 */
public class OpencourseManage {

	public static Boolean insertNewOpencourse(OpenCourseInfo opencourse) {
		Boolean result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
			opencourse.setId(openCourseMapper.getMaxId() + 1);
			result = openCourseMapper.insertNewOpencourse(opencourse);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static Boolean deleteOpencourse(Integer id) {
		Boolean result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
			result = openCourseMapper.deleteOpencourse(id);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<OpenCourseInfo> getOpenCourseList() {
		List<OpenCourseInfo> result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
			result = openCourseMapper.getOpenCourseList();
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
}
