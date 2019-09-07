package tech.zxuuu.server.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IBookMapper;
import tech.zxuuu.entity.Book;
import tech.zxuuu.server.main.App;

/**
 * 图书馆后端
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class BookServer {

	public static String searchAuthorByTitle(String title) {
		String result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			result = bookMapper.searchAuthorByTitle(title);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static int borrowBook(String borrower, String ISBN) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			String TITLE = bookMapper.searchTitleByISBN(ISBN);
			if (TITLE != null) {
				int chargable = bookMapper.searchChargableByISBN(ISBN);
				result = 1;
				if (chargable == 1) {
					String nowborrower = bookMapper.getBorrowerByISBN(ISBN);
					if (nowborrower == null || !nowborrower.equals(borrower)) {
						return 1;
					}
					bookMapper.changeChargableByISBN(ISBN);
					String title = bookMapper.searchTitleByISBN(ISBN);
					bookMapper.changeNumberByTitle(title);
					Map<String, String> map = new HashMap<String, String>();
					map.put("borrower", borrower);
					map.put("ISBN", ISBN);
					bookMapper.changeBorrowerByISBN(map);
					result = 2;
				}
			}
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<Book> searchBeBorrowed(String borrower) {
		List<Book> list = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			list = bookMapper.searchBeBorrowed(borrower);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return list;
	}

	public static List<Book> fuzzySearchByTitleAndAuthor(String title, String author) {
		List<Book> list = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			if (author == null) {
				list = bookMapper.fuzzySearchByTitle(title);
				sqlSession.commit();
				sqlSession.close();
				return list;
			} else if (title == null) {
				list = bookMapper.fuzzySearchByAuthor(author);
				sqlSession.commit();
				sqlSession.close();
				return list;
			} else {
				Book book = new Book();
				book.setTitle(title);
				book.setAuthor(author);
				list = bookMapper.fuzzySearchByTitleAndAuthor(book);
				sqlSession.commit();
				sqlSession.close();
				return list;
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return list;
	}

	public static int returnBook(String borrower, String ISBN) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();

			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			String TITLE = bookMapper.searchTitleByISBN(ISBN);

			if (TITLE != null) {
				int chargable = bookMapper.searchChargableByISBN(ISBN);

				if (chargable == 1)
					result = 1;
				else {
					String nowborrower = bookMapper.getBorrowerByISBN(ISBN);

					if (nowborrower == null || !nowborrower.equals(borrower)) {
						result = 1;
					} else {
						bookMapper.returnBookByISBN(ISBN);
						result = 2;
					}
				}
			}
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}

		return result;

	}

	public static Boolean addBook(String ISBN, String title, String author, String category, String details,
			String pictureURL) {
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			int number = bookMapper.searchHowManyByISBN(ISBN);
			if (number == 1) {
				sqlSession.close();
				return false;
			} else {
				Book book2 = new Book();
				book2.setISBN(ISBN);
				book2.setTitle(title);
				book2.setAuthor(author);
				book2.setCategory(category);
				book2.setDetails(details);
				book2.setPictureURL(pictureURL);
				bookMapper.addBook(book2);
				sqlSession.commit();
				sqlSession.close();
				return true;
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public static int searchHowManyByISBN(String ISBN) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			Book book = new Book();
			book.setISBN(ISBN);
			result = bookMapper.searchHowManyByISBN(ISBN);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<Book> searchSimilarBook(String title, String category) {
		List<Book> list = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			Book book = new Book();
			book.setTitle(title);
			book.setCategory(category);
			list = bookMapper.searchSimilarBook(book);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String searchPicture(String ISBN) {
		String result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			result = bookMapper.searchPicture(ISBN);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static List<Book> searchHotBook() {
		List<Book> list = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			list = bookMapper.searchHotBook();
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return list;

	}

	public static Boolean deleteBook(String ISBN) {
		Boolean result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			Book book = new Book();
			book.setISBN(ISBN);
			result = bookMapper.deleteBook(book);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static int renewBook(String ISBN) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			result = bookMapper.checkBorrowTime(ISBN);
			if (result >= 30)
				result = 0;
			else {
				int renewornot = bookMapper.checkRenewOrNot(ISBN);
				if (renewornot == 1)
					result = 1;
				else {
					bookMapper.renewBook(ISBN);
					result = 2;
				}
			}
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

}
