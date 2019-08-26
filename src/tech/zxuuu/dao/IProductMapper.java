package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.Product;

public interface IProductMapper {
	public List<Product> searchProduct(String product);


}
