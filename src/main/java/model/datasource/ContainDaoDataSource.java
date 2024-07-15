package model.datasource;


import model.bean.ContainBean;
import model.dao.IContainDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ContainDaoDataSource implements IContainDao {
	
	static private final String TABLE_NAME="contiene";
	private DataSource ds=null;
	
	public ContainDaoDataSource(DataSource ds) 
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(ContainBean contain) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + ContainDaoDataSource.TABLE_NAME 
				+ " (utente, codice_ordine, codice_prodotto) VALUES (?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, contain.getUtente());
			preparedStatement.setInt(2, contain.getCodiceOrdine());
			preparedStatement.setInt(3, contain.getCodiceProdotto());

			
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
	public boolean doDelete(String user, int orderID) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ContainDaoDataSource.TABLE_NAME + " WHERE UTENTE = ? AND CODICE_ORDINE = ?";
		
		try {
			connection= ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, user);
			preparedStatement.setInt(2, orderID);
			
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
	public ContainBean doRetrieveByKey(String user, int orderID) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ContainBean bean= new ContainBean();
		String selectSQL = "SELECT * FROM " + ContainDaoDataSource.TABLE_NAME + " WHERE UTENTE = ? AND CODICE_ORDINE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			preparedStatement.setInt(2, orderID);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUtente(rs.getString("Utente"));
				bean.setCodiceOrdine(rs.getInt("Codice_Ordine"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
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
	public Collection<ContainBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ContainBean> items= new LinkedList<ContainBean>();
		String selectSQL = "SELECT * FROM " + ContainDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL +=" ORDER BY" + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ContainBean  bean = new ContainBean();
				
				bean.setUtente(rs.getString("Utente"));
				bean.setCodiceOrdine(rs.getInt("Codice_Ordine"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				items.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return items;
	}

	@Override
	public Collection<ContainBean> doRetrieveByOrder(String user, int orderID) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ContainBean> items= new LinkedList<ContainBean>();
		String selectSQL = "SELECT * FROM " + ContainDaoDataSource.TABLE_NAME + " WHERE UTENTE = ? AND CODICE_ORDINE = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			preparedStatement.setInt(2, orderID);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ContainBean  bean = new ContainBean();
				
				bean.setUtente(rs.getString("Utente"));
				bean.setCodiceOrdine(rs.getInt("Codice_Ordine"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				items.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return items;
	}

}
