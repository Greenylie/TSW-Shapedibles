package model.dao;

import model.bean.ProductBean;

import java.sql.SQLException;
import java.util.Collection;

public interface IProductDao {
	public void doSave(ProductBean product) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;
	
	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
}