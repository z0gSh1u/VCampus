package tech.zxuuu.server.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IProductMapper;
import tech.zxuuu.entity.Product;
import tech.zxuuu.server.main.App;
import java.util.ArrayList;

public class ProductServer {
	public static List<Product> searchProduct(String product) {
		List<Product> result = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.searchProduct(product);

			sqlSession.commit();// 提交查询

		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}

	public static List<Product> listProductByType(String type) {
		List<Product> result = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.listProductByType(type);

			sqlSession.commit();// 提交查询
		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Product> manageListProduct(Product product) {
		List<Product> result = new ArrayList<>();

		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.manageListProduct(product);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}
	
	public static Boolean addBuyer(Product product) {
		Boolean result = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.addBuyer(product);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}
	public static List<Product> searchBuyer(String buyer) {
		List<Product> result = null;

		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.searchBuyer(buyer);

			sqlSession.commit();// 提交查询

		} catch (Exception e) {
			// sqlSession.rollback();
			e.printStackTrace();
		}
		return result;

	}
	
	

}
