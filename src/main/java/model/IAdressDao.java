package model;

import java.sql.SQLException;
import java.util.Collection;

public interface IAdressDao {
    public void doSave(AdressBean adress) throws SQLException;
	
	public boolean doDelete(String id, String user) throws SQLException;
	
	public AdressBean doRetrieveByKey(String id, String user) throws SQLException;
	
	public Collection<AdressBean> doRetrieveAll(String order) throws SQLException;
}
