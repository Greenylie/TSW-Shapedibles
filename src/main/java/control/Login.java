package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.datasource.UserDaoDataSource;
import model.bean.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		
		UserBean userCheck = new UserBean();
		UserBean userChecked = new UserBean();
		
		try {
			userChecked.setUsername(username);
			userChecked.setPass(password);
			
			DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
			UserDaoDataSource userDao = new UserDaoDataSource(ds);
			
			userCheck= userDao.doRetrieveByKey(username);
			
			if(userChecked.getUsername().equals(userCheck.getUsername())) 
			{
				if(hashPassword(userChecked.getPass()).equals(userCheck.getPass())) 
				{
					System.out.println("Sei loggato!");
				}
				else System.out.println("Password errata!");
			}
			else System.out.println("Username errato!" );
			
		}
		catch(SQLException e)
		{
			System.out.println("Error..." + e.getMessage());
		}
		catch(NoSuchAlgorithmException e)
		{
			System.out.println("Error..." + e.getMessage());
		}
		
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
	    StringBuilder sb = new StringBuilder();
	    for (byte b : hashedBytes) {
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	   }

	
}
