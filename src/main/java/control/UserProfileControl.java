package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.bean.ContainBean;
import model.bean.OrderBean;
import model.bean.UserBean;
import model.dao.IContainDao;
import model.dao.IOrderDao;
import model.dao.IUserDao;
import model.datasource.ContainDaoDataSource;
import model.datasource.OrderDaoDataSource;
import model.datasource.UserDaoDataSource;

/**
 * Servlet implementation class UserProfileControl
 */
@WebServlet("/UserProfileControl")
public class UserProfileControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileControl() {
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
		IOrderDao orderDao = null;
		IContainDao containDao = null;
		
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		userDao = new UserDaoDataSource(ds);
		orderDao = new OrderDaoDataSource(ds);
		containDao= new ContainDaoDataSource(ds);
		
		String action = request.getParameter("action");
		UserBean user = (UserBean) request.getSession().getAttribute("loggedUser");
		
		
		try {
			
			Collection<OrderBean> Ordini =  orderDao.doRetrieveByUser(user.getUsername());
			
			
			if(action != null){
				if(action.equalsIgnoreCase("orderDetails")) {
					int orderNum = Integer.parseInt(request.getParameter("orderNum"));
					String orderUser = request.getParameter("orderUser");
					Collection<ContainBean> items = containDao.doRetrieveByOrder(orderUser, orderNum);
					request.removeAttribute("Details");
					request.setAttribute("Details", items);
				} 
			}
			
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		
		
		try {
			request.removeAttribute("OrdersLoggedUser");
			request.setAttribute("OrdersLoggedUser", orderDao.doRetrieveByUser(user.getUsername()));
		} catch (SQLException e) {
			System.out.println("Error; " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
		dispatcher.forward(request, response);
	}

}
