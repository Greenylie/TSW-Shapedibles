package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.IProductDao;
import model.ProductBean;


public class ProductDaoDriverMan implements IProductDao
{
	private static final String TABLE_NAME = "prodotti";
	private DriverManagerConnectionPool dmcp= null;
	
	public ProductDaoDriverMan(DriverManagerConnectionPool dmcp)
	{
		this.dmcp=dmcp;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(ProductBean product) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + ProductDaoDriverMan.TABLE_NAME 
				+ " (nome, costo, descrizione, disponibilità , tipologia) VALUES (?,?,?,?,?)";
		
		try {
			connection = dmcp.getConnection();
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
				dmcp.releaseConnection(connection);;
			}
		}
		
		
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FORM" + ProductDaoDriverMan.TABLE_NAME + " WHERE CODE = ?";
		
		try {
			connection= dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			
			result = preparedStatement.executeUpdate();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
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
		String selectSQL = "SELECT * FROM " + ProductDaoDriverMan.TABLE_NAME + "WHERE CODE = ?";
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCosto(rs.getDouble("COSTO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setDisponibilità(rs.getInt("QUANTITÀ"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			dmcp.releaseConnection(connection);
		}
		}
		
		return bean;
	}

	@Override
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProductBean> products= new LinkedList<ProductBean>();
		String selectSQL = "SELECT * FROM " + ProductDaoDriverMan.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL +=" ORDER BY" + order;
		}
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCosto(rs.getDouble("COSTO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setDisponibilità(rs.getInt("QUANTITÀ"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			dmcp.releaseConnection(connection);
		}
		}
		
		return products;
	}
 
}
