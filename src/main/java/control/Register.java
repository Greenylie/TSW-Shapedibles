package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.bean.UserBean;
import model.datasource.UserDaoDataSource;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String nomeCognome= request.getParameter("nome_cognome");
		String sesso= request.getParameter("sesso");
		String paese= request.getParameter("paese");
		String dataNascista= request.getParameter("data_nascita");
		int isAdmin= 0;
		
		UserBean user= new UserBean();
		
		try {
			user.setUsername(username);
			user.setEmail(email);
			user.setPass(hashPassword(password));
			user.setNomeCognome(nomeCognome);
			user.setSesso(sesso);
			user.setPaese(paese);
			user.setDataNascita(dataNascista);
			user.setUserAdmin(isAdmin);
			
			DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
			UserDaoDataSource userDao = new UserDaoDataSource(ds);
			userDao.doSave(user);
			
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
