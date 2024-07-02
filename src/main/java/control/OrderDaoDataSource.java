package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.IOrderDao;
import model.OrderBean;

public class OrderDaoDataSource implements IOrderDao
{
	private static final String TABLE_NAME="Ordini";
	private DataSource ds=null;
	
	public OrderDaoDataSource(DataSource ds)
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(OrderBean order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + OrderDaoDataSource.TABLE_NAME 
				+ " (utente, codice, indirizzo, stato, data_ordine, saldo-totale) VALUES (?,?,?,?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, order.getUtente());
			preparedStatement.setInt(2, order.getCodice());
			preparedStatement.setString(3, order.getIndirizzo());
			preparedStatement.setString(4, order.getStato());
			preparedStatement.setString(5, order.getDataOrdine());
			preparedStatement.setDouble(6, order.getSaldoTotale());

			
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
	public boolean doDelete(String user, int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE CODICE = ? AND USER = ?";
		
		try {
			connection= ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, user);
			
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
	public OrderBean doRetrieveByKey(String user, int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		OrderBean bean= new OrderBean();
		String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE CODICE = ? AND USER = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, user);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUtente(rs.getString("Utente"));
				bean.setCodice(rs.getInt("codice"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setStato(rs.getString("stato"));
				bean.setDataOrdine(rs.getString("data_ordine"));
				bean.setSaldoTotale(rs.getDouble("saldo_totale"));
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
	public Collection<OrderBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<OrderBean> orders= new LinkedList<OrderBean>();
		String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL +=" ORDER BY" + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				OrderBean  bean = new OrderBean();
				
				bean.setUtente(rs.getString("Utente"));
				bean.setCodice(rs.getInt("codice"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setStato(rs.getString("stato"));
				bean.setDataOrdine(rs.getString("data_ordine"));
				bean.setSaldoTotale(rs.getDouble("saldo_totale"));
				orders.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return orders;
	}
	
}
