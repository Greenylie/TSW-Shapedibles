package model.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import model.bean.ImageBean;
import model.dao.IImageDao;

public class ImageDaoDataSource implements IImageDao
{
	private static final String TABLE_NAME="imagine";
	private DataSource ds=null;
	
	public ImageDaoDataSource(DataSource ds)
	{
		this.ds=ds;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(ImageBean image) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO " + ImageDaoDataSource.TABLE_NAME 
				+ " (img, Codice_prodotto) VALUES (?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, image.getImg());
			preparedStatement.setInt(2, image.getCodiceProdotto());
			
			

			
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
		
		String deleteSQL = "DELETE FROM " + ImageDaoDataSource.TABLE_NAME + " WHERE Codice_Prodotto = ? AND  Num_Imagine = ?";
		
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
	public ImageBean doRetrieveByKey(int num, int codice) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ImageBean bean= new ImageBean();
		String selectSQL = "SELECT * FROM " + ImageDaoDataSource.TABLE_NAME + " WHERE Codice_Prodotto = ? AND  Num_Imagine = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setInt(2, num);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setNumImage(rs.getInt("Num_Imagine"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				bean.setImg(rs.getString("img"));  
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
	public Collection<ImageBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ImageBean> images= new LinkedList<ImageBean>();
		String selectSQL = "SELECT * FROM " + ImageDaoDataSource.TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL +=" ORDER BY" + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ImageBean  bean = new ImageBean();
				
				bean.setNumImage(rs.getInt("Num_Imagine"));
				bean.setCodiceProdotto(rs.getInt("Codice_Prodotto"));
				bean.setImg(rs.getString("img"));  
				images.add(bean);
			}
			
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
		} finally{
			connection.close();
		}
		}
		
		return images;
	}
}
