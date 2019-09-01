package tech.zxuuu.server.opencourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IOpenCourseMapper;
import tech.zxuuu.dao.IStudentMapper;
import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.server.main.App;

public class StuMenu {

	//获取公开课列表
	public static List<OpenCourseInfo> getCourseList(){
		List<OpenCourseInfo> list = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();

			IOpenCourseMapper openCourseMapper = sqlSession.getMapper(IOpenCourseMapper.class);
			list = openCourseMapper.getOpenCourseList();

			sqlSession.commit();

		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return list;
	}
}
