package tech.zxuuu.server.shop;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.zxuuu.dao.IProductMapper;
import tech.zxuuu.entity.Product;
import tech.zxuuu.server.main.App;

public class ProductServer {
	public static List<Product> searchProduct(String product) {
		List<Product> result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IProductMapper productMapper = sqlSession.getMapper(IProductMapper.class);
			result = productMapper.searchProduct(product);
			
			sqlSession.commit();
			}
		catch(Exception e) {
				// sqlSession.rollback();
				e.printStackTrace();
			}
		return result;
		
			}
	

}
