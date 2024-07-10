package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.ImageBean;

public interface IImageDao {
    
	public void doSave(ImageBean image) throws SQLException;
	
	public boolean doDelete(int num, int codice) throws SQLException;
	
	public ImageBean doRetrieveByKey(int num, int codice) throws SQLException;
	
	public Collection<ImageBean> doRetrieveAll(String order) throws SQLException;
}
