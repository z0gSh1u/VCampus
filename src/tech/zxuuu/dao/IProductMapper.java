package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.Product;

public interface IProductMapper {
	public List<Product> searchProduct(String p_name);

	/* public String searchTypeByP_name(String p_name); */
    public List<Product> listProductByType(String type); 
    public List<Product> cartProduct(String buyer);


}
