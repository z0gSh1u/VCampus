package tech.zxuuu.util;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0DataSourceFactory extends UnpooledDataSourceFactory {
	
	public c3p0DataSourceFactory() {
		this.dataSource = new ComboPooledDataSource();
	}
	
}
