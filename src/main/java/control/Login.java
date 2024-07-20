package control;

import model.bean.UserBean;
import model.datasource.UserDaoDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Make doGet dispatch the user to the loginView page
		request.getRequestDispatcher("/WEB-INF/jsp/pages/loginView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		
		UserBean userCheck = new UserBean();
		
		try {
			
			DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
			UserDaoDataSource userDao = new UserDaoDataSource(ds);
			
			userCheck= userDao.doRetrieveByKey(username);
			
			if(username.equals(userCheck.getUsername()) && hashPassword(password).equals(userCheck.getPass())) 
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("LoggedUser", userCheck);
				String redirectURL = (String) session.getAttribute("redirectURL");
				if(redirectURL != null) {
					session.removeAttribute("redirectURL");
					response.sendRedirect(redirectURL);
				} else {
					// Default redirect if no stored URL
					response.sendRedirect(request.getContextPath() + "/Home");
				}
				
			}
			 else response.sendRedirect(request.getContextPath() + "/.jsp");
			
		}
		catch(SQLException e)
		{
			request.setAttribute("error",  "Error: c'è stato un errore nel autentificazione, assicurarsi di inserire i campi corretamente.");
	 		response.sendError(500, "Error: " + e.getMessage());
		}
		catch(NoSuchAlgorithmException e)
		{
			request.setAttribute("error",  "Error: sembra esserci un problema con il login, se persiste contattare l'assistenza.");
	 		response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());
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
