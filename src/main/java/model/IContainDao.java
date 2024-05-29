package model;

import java.sql.SQLException;
import java.util.Collection;

public interface IContainDao {
	public void doSave(ContainBean contain) throws SQLException;
	
	public boolean doDelete(String user, String orderID) throws SQLException;
	
	public AdressBean doRetrieveByKey(String user, String orderID) throws SQLException;
	
	public Collection<ContainBean> doRetrieveAll(String order) throws SQLException;
}
