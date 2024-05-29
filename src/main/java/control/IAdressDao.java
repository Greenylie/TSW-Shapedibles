package control;

import java.sql.SQLException;
import java.util.Collection;
import model.AdressBean;

public interface IAdressDao {
    public void doSave(AdressBean product) throws SQLException;
	
	public boolean doDelete(String id, String user) throws SQLException;
	
	public AdressBean doRetrieveByKey(String id, String user) throws SQLException;
	
	public Collection<AdressBean> doRetrieveAll(String order) throws SQLException;
}
