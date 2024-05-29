package control;

import java.sql.SQLException;
import java.util.Collection;
import model.UserBean;

public interface IUserDao {
    public void doSave(UserBean user) throws SQLException;
	
	public boolean doDelete(String username) throws SQLException;
	
	public UserBean doRetrieveByKey(String username) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException;
}
