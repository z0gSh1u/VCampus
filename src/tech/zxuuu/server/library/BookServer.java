package tech.zxuuu.server.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.org.glassfish.external.statistics.Statistic;

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
	public static List<Book> fuzzySearchByTitleAndAuthor(String title,String author)
	{
		List<Book> list=new ArrayList<>();
		SqlSession sqlSession=null;
		try {
			sqlSession=App.sqlSessionFactory.openSession();
			IBookMapper bookMapper=sqlSession.getMapper(IBookMapper.class);
			if(author==null)
			{
				list=bookMapper.fuzzySearchByTitle(title);
				sqlSession.commit();
				return list;
			}
			else if(title==null) {
				list=bookMapper.fuzzySearchByAuthor(author);
				sqlSession.commit();
				return list;
			}
			else {
				Book book=new Book();
				book.setTitle(title);
				book.setAuthor(author);
				list=bookMapper.fuzzySearchByTitleAndAuthor(book);
				sqlSession.commit();
				return list;
			}
		}
		catch(Exception e){
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
	public static Boolean addBook(String ISBN,String title,String author,String category,String details)
	{
		
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			int number = bookMapper.searchHowManyByISBN(ISBN);
			
			System.out.println("num="+number);
			
			if (number == 1) {
				return false;
				
			} else {
				Book book2 = new Book();
				book2.setISBN(ISBN);
				book2.setTitle(title);
				book2.setAuthor(author);
				book2.setCategory(category);
				book2.setDetails(details);
				bookMapper.addBook(book2);
				sqlSession.commit();
				return true;
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean deleteBook(String ISBN)
	{
		Boolean result=null;
		SqlSession sqlSession=null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
            Book book=new Book();
            book.setISBN(ISBN);
			result = bookMapper.deleteBook(book);
			sqlSession.commit();
			} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	public static int searchHowManyByISBN(String ISBN)
	{
		int result=0;
		SqlSession sqlSession=null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
            Book book=new Book();
            book.setISBN(ISBN);
			result = bookMapper.searchHowManyByISBN(ISBN);
			sqlSession.commit();
			} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	

}


