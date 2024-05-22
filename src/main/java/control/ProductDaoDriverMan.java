package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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
		}
		
		
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductBean doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
 
}
