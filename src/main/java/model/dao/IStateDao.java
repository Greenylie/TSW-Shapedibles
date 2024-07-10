package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.bean.StateBean;

public interface IStateDao {
    public void doSave(StateBean state) throws SQLException;
	
	public boolean doDelete(int num, int codice) throws SQLException;
	
	public StateBean doRetrieveByKey(int num, int codice) throws SQLException;
	
	public Collection<StateBean> doRetrieveAll(String order) throws SQLException;
}
