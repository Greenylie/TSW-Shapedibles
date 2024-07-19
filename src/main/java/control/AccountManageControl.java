package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.bean.UserBean;
import model.dao.IUserDao;
import model.datasource.UserDaoDataSource;

/**
 * Servlet implementation class AccountManageControl
 */
@WebServlet("/AccountManageControl")
public class AccountManageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManageControl() {
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
		IUserDao userDao = null;
		
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		userDao = new UserDaoDataSource(ds);
		
		String action = request.getParameter("action");
		
		try {
			if(action != null){
				if(action.equalsIgnoreCase("admin")) {
					UserBean bean;
					String username = request.getParameter("username");
					bean = userDao.doRetrieveByKey(username);
					bean.setUserAdmin(1);
					userDao.doDelete(bean.getUsername());
					userDao.doSave(bean);
				} else if(action.equalsIgnoreCase("delete")) {
					String username = request.getParameter("username");
					userDao.doDelete(username);
				}
			}
			
			} catch(SQLException e) {
				request.setAttribute("error",  "Error: c'è stato un errore nel elaborazione degli utenti.");
		 		response.sendError(500, "Error: " + e.getMessage());
			}
		
		
		try {
			request.removeAttribute("users");
			request.setAttribute("users", userDao.doRetrieveAll(""));
		} catch (SQLException e) {
			request.setAttribute("error",  "Error: c'è stato un errore nel recupero dei dati degli utenti.");
	 		response.sendError(500, "Error: " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AccountManagement.jsp");
		dispatcher.forward(request, response);
	}

}
