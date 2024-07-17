package model.datasource;

import model.bean.ProductBean;
import model.dao.IProductDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class ProductDaoDataSource implements IProductDao
{
	private static final String TABLE_NAME = "prodotti";
	private final DataSource ds;
	
	public ProductDaoDataSource(DataSource ds)
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(ProductBean product) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + ProductDaoDataSource.TABLE_NAME 
				+ " (info_correnti, nome) VALUES (?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getInfoCorrenti());
			preparedStatement.setString(2, product.getNome());
			
			
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					 preparedStatement.close();
			} finally {
                assert connection != null;
                connection.close();
			}
		}
		
		
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		
		String deleteSQL = "DELETE FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE CODICE = ?";
		
		try {
			connection= ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			
			result = preparedStatement.executeUpdate();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
                assert connection != null;
                connection.close();
			}
		}
		return (result!=0);
	}

	@Override
	public ProductBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductBean bean= new ProductBean();
		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE CODICE= ? ";
		
		try {
			//if(ds==null) System.out.println("ds nulla.");
			connection = ds.getConnection();
			//if(connection==null) System.out.println("connesione nulla.");
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setInfoCorrenti(rs.getInt("INFO_CORRENTI"));	
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
                assert connection != null;
                connection.close();
		}
		}
		
		return bean;
	}
	@Override
	public ProductBean doRetrieveByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductBean bean= new ProductBean();
		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE NOME= ? ";
		
		try {
			//if(ds==null) System.out.println("ds nulla.");
			connection = ds.getConnection();
			//if(connection==null) System.out.println("connesione nulla.");
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setInfoCorrenti(rs.getInt("INFO_CORRENTI"));	
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
                assert connection != null;
                connection.close();
		}
		}
		
		return bean;
	}
	
	@Override
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProductBean> products= new LinkedList<>();
		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.isEmpty()) {
			selectSQL +=" ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setInfoCorrenti(rs.getInt("INFO_CORRENTI"));	
				products.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
                assert connection != null;
                connection.close();
		}
		}
		
		return products;
	}
	

	@Override
	public List<ProductBean> searchByName(String query) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		List<ProductBean> products = new LinkedList<>();
		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE NOME LIKE ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%" + query + "%");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setInfoCorrenti(rs.getInt("INFO_CORRENTI"));
				products.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
                assert connection != null;
                connection.close();
		}
		}
		
		return products;
	}

}
