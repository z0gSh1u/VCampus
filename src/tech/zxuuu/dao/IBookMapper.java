package tech.zxuuu.dao;
import java.util.Map;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import java.util.List;
import tech.zxuuu.entity.*;

public interface IBookMapper {//基本的数据库操作；

	public String searchAuthorByTitle(String title);
    
	public List<String> searchByTitle(String title);
	
	public int searchHowManyByISBN(String ISBN);
    
    public Boolean changeChargableByISBN(String ISBN);
    public String  searchTitleByISBN(String ISBN);
    public Boolean changeNumberByTitle(String title);
    
    public Boolean returnBookByISBN(String ISBN);
    
    public List<Book> searchBeBorrowed(String borrower); 
    public List<Book> fuzzySearchByTitle(String title); 
    public List<Book> fuzzySearchByAuthor(String author);
    public List<Book> fuzzySearchByTitleAndAuthor(Book book); 
    
    public Boolean addBook(Book book);
    public Boolean deleteBook(Book book);
	
}
