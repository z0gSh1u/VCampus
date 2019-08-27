package tech.zxuuu.server.library;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IBookMapper;
import tech.zxuuu.server.main.App;

public class Book {

	public static String searchAuthorByTitle(String title) {
//		CLIENT
//		
//		REQ, RESP
//		
//		SERVER
//		 
//		  DAO
//		 
//		DATABASE

		String result = null;

		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);

			result = bookMapper.searchAuthorByTitle(title);
			sqlSession.commit();

		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}

}
