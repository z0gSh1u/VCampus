package tech.zxuuu.dao;

import java.util.List;
import java.util.Map;

import tech.zxuuu.entity.Product;

public interface IProductMapper {

	public List<Product> listProductByType(String type);

	public List<Product> searchProduct(String product);

	public Boolean insertNewProcut(Product product);

	public Boolean deleteProduct(Product product);

	public List<Product> manageListProduct(Product product);

	public List<Product> searchBuyer(String buyer);

	public Boolean addBuyer(Product product);
	
	public String getProductStorage(String name);
	
	public int buySomething(Map<String, String> map);
	
}
