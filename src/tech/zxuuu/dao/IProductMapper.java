package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.Product;

public interface IProductMapper {
<<<<<<< HEAD
	public List<Product> searchProduct(String p_name);

	/* public String searchTypeByP_name(String p_name); */
    public List<Product> listProductByType(String type); 
    public List<Product> cartProduct(String buyer);
=======
	public List<Product> searchProduct(String product);
>>>>>>> 34e7912ff789559df32cb4e6a2ca946e890d0d4b


}
