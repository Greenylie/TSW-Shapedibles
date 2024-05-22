package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ProductControl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		boolean isDataSource = false;
		IProductDao productDao= null;
		
		if(isDataSource)
		{
			DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
}
