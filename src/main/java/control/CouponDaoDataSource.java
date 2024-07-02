package control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.ICouponDao;
import model.CouponBean;

public class CouponDaoDataSource implements ICouponDao{
  
	private static final String TABLE_NAME="coupons";
	private DataSource ds=null;
	
	public CouponDaoDataSource(DataSource ds)
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}

	@Override
	public void doSave(CouponBean coupon) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + CouponDaoDataSource.TABLE_NAME 
				+ " (codice, percentuale_sconto, saldo_minimo) VALUES (?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, coupon.getCodice());
			preparedStatement.setInt(2, coupon.getPercentualeSconto());
			preparedStatement.setDouble(3, coupon.getSaldoMinimo());
			
			preparedStatement.executeUpdate();
		} 
		finally {
			try {
				if (preparedStatement != null)
					 preparedStatement.close();
			} finally {
				connection.close();;
			}
		}
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + CouponDaoDataSource.TABLE_NAME + " WHERE CODICE = ?";
		
		try {
			connection= ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);
			
			result = preparedStatement.executeUpdate();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				connection.close();
			}
		}
		return (result!=0);
	}

	
	@Override
	public CouponBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		CouponBean bean= new CouponBean();
		String selectSQL = "SELECT * FROM " + CouponDaoDataSource.TABLE_NAME + "WHERE CODICE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getString("CODICE"));
				bean.setPercentualeSconto(rs.getInt("PERCENTUALE_SCONTO"));
				bean.setSaldoMinimo(rs.getDouble("SALDO_MINIMO"));
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return bean;
	}

	@Override
	public Collection<CouponBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				
				Collection<CouponBean> coupons= new LinkedList<CouponBean>();
				String selectSQL = "SELECT * FROM " + CouponDaoDataSource.TABLE_NAME;
				
				if(order != null && !order.equals("")) {
					selectSQL +=" ORDER BY" + order;
				}
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						CouponBean  bean = new CouponBean();
						
						bean.setCodice(rs.getString("CODICE"));
						bean.setPercentualeSconto(rs.getInt("PERCENTUALE_SCONTO"));
						bean.setSaldoMinimo(rs.getDouble("SALDO_MINIMO"));
						coupons.add(bean);
					}
					
				} finally {
					try{
						if(preparedStatement != null)
							preparedStatement.close();
				} finally{
					connection.close();
				}
				}
				
				return coupons;
  }

}
