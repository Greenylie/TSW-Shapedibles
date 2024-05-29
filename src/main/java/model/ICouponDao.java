package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ICouponDao {
    public void doSave(CouponBean coupon) throws SQLException;
	
	public boolean doDelete(String code) throws SQLException;
	
	public CouponBean doRetrieveByKey(String code) throws SQLException;
	
	public Collection<CouponBean> doRetrieveAll(String order) throws SQLException;
}
