package tech.zxuuu.dao;

import java.util.List;

public interface IBookMapper {

	public String searchAuthorByTitle(String title);
    public List<String> searchByTitle(String title);
	
}
