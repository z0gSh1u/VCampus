package tech.zxuuu.server.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import tech.zxuuu.dao.IBookMapper;
import tech.zxuuu.entity.Book;
import tech.zxuuu.server.main.App;

public class BookServer {

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
		SqlSession sqlSession=null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);

			result = bookMapper.searchAuthorByTitle(title);
			sqlSession.commit();
			
			

		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}
	public static Boolean borrowBook(String ISBN)
	{
		Boolean result=null;
		SqlSession sqlSession = null;
		try {
			sqlSession =App.sqlSessionFactory.openSession();//连接数据库
			IBookMapper bookMapper =sqlSession.getMapper(IBookMapper.class);//取了Mapper，知道要执行哪个查询
			bookMapper.changeChargableByISBN(ISBN);
			String title=bookMapper.searchTitleByISBN(ISBN);
			bookMapper.changeNumberByTitle(title);//一次操作可以有多次数据库操作；
			 
			sqlSession.commit();//提交查询
		    }
	    catch(Exception e) {
	    	sqlSession.rollback();
	    	e.printStackTrace();
	    }
		return result;
	}
	
	public static List<Book> searchBeBorrowed(String borrower)
	{
		List<Book>  list=new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession =App.sqlSessionFactory.openSession();//连接数据库
			IBookMapper bookMapper =sqlSession.getMapper(IBookMapper.class);//取了Mapper，知道要执行哪个查询
			
			list=bookMapper.searchBeBorrowed(borrower);
			sqlSession.commit();//提交查询
		}
	    catch(Exception e) {
	    	sqlSession.rollback();
	    	e.printStackTrace();
	    }
		return list;
	}
	
	public static Boolean returnBook(String ISBN)
	{
		Boolean result=null;
		SqlSession sqlSession=null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);

			result = bookMapper.returnBookByISBN(ISBN);
			sqlSession.commit();
			} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

		
	}
	

}


