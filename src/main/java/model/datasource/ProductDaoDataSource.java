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
	private DataSource ds= null;
	
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
				+ " (nome, costo, descrizione, disponibilità , tipologia) VALUES (?,?,?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setDouble(2, product.getCosto());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setInt(4, product.getDisponibilità());
			preparedStatement.setString(5, product.getTipologia());
			
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					 preparedStatement.close();
			} finally {
				connection.close();
			}
		}
		
		
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
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
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCosto(rs.getDouble("COSTO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setDisponibilità(rs.getInt("DISPONIBILITÀ"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
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
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProductBean> products= new LinkedList<ProductBean>();
		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
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
				bean.setCosto(rs.getDouble("COSTO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setDisponibilità(rs.getInt("DISPONIBILITÀ"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
				products.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return products;
	}
	

	@Override
	public List<ProductBean> searchByName(String query) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		List<ProductBean> products = new LinkedList<ProductBean>();
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
				bean.setCosto(rs.getDouble("COSTO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setDisponibilità(rs.getInt("DISPONIBILITÀ"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
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
