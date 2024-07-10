package model.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.bean.StateBean;
import model.dao.IStateDao;

public class StateDaoDataSource implements IStateDao
{
	private static final String TABLE_NAME="imagine";
	private DataSource ds=null;
	
	public StateDaoDataSource(DataSource ds)
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(StateBean state) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + StateDaoDataSource.TABLE_NAME 
				+ " (img, Codice_prodotto, nome, costo) VALUES (?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, state.getImg());
			preparedStatement.setInt(2, state.getCodiceProdotto());
			preparedStatement.setString(3, state.getNome());
			preparedStatement.setDouble(4, state.getCosto());
			
			

			
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
	public boolean doDelete(int num, int codice) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + StateDaoDataSource.TABLE_NAME + " WHERE Codice_Prodotto = ? AND  Num_Stato = ?";
		
		try {
			connection= ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setInt(2, num);
			
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
	public StateBean doRetrieveByKey(int num, int codice) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		StateBean bean= new StateBean();
		String selectSQL = "SELECT * FROM " + StateDaoDataSource.TABLE_NAME + " WHERE Codice_Prodotto = ? AND  Num_Stato = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setInt(2, num);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setNumState(rs.getInt("Num_Stato"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				bean.setImg(rs.getString("img"));  
				bean.setNome(rs.getString("nome"));
				bean.setCosto(rs.getDouble("costo"));  
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
	public Collection<StateBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<StateBean> states= new LinkedList<StateBean>();
		String selectSQL = "SELECT * FROM " + StateDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL +=" ORDER BY" + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				StateBean  bean = new StateBean();
				
				bean.setNumState(rs.getInt("Num_Stato"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				bean.setImg(rs.getString("img"));  
				bean.setNome(rs.getString("nome"));
				bean.setCosto(rs.getDouble("costo"));  
				states.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return states;
	}
}
