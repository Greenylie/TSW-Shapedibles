package control;
import model.bean.ContainBean;
import model.dao.IContainDao;
import model.datasource.ContainDaoDataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Cart;
import model.bean.OrderBean;
import model.dao.IOrderDao;
import model.datasource.OrderDaoDataSource;

/**
 * Servlet implementation class OrdersControl
 */
@WebServlet("/OrdersControl")
public class OrdersControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDateTime now = LocalDateTime.now();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersControl() {
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
        IOrderDao orderDao = null;
		IContainDao containDao = null;
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		orderDao = new OrderDaoDataSource(ds);
		containDao = new ContainDaoDataSource(ds);
		String action = request.getParameter("action");
		
		try {
		if(action != null){
			if(action.equalsIgnoreCase("UserFilter")) {
				String user = request.getParameter("user");
				request.removeAttribute("orders");
				request.setAttribute("orders", orderDao.doRetrieveByUser(user));
				
			}  else if(action.equalsIgnoreCase("DateFilter")) {
				Date dateMax = Date.valueOf(request.getParameter("dateMax"));
				Date dateMin = Date.valueOf(request.getParameter("dateMin"));
				Collection<OrderBean> orders = orderDao.doRetrieveAll("");
				Iterator<?> it = orders.iterator();
				Collection<OrderBean> orderNew= new LinkedList<OrderBean>();
				while(it.hasNext()) {
					OrderBean bean = (OrderBean) it.next();
					if(isRightDate(Date.valueOf(bean.getDataOrdine()), dateMin, dateMax)) orderNew.add(bean);
				}
				request.removeAttribute("orders");
				request.setAttribute("orders", orderNew);
				
		    }  else if(action.equalsIgnoreCase("User-DateFilter")) {
		    	String user = request.getParameter("user");
		    	Date dateMax = Date.valueOf(request.getParameter("dateMax"));
				Date dateMin = Date.valueOf(request.getParameter("dateMin"));
		    	Collection<OrderBean> orderUs = orderDao.doRetrieveByUser(user);
		    	Iterator<?> it = orderUs.iterator();
				Collection<OrderBean> orderNew= new LinkedList<OrderBean>();
				while(it.hasNext()) {
					OrderBean bean = (OrderBean) it.next();
					if(isRightDate(Date.valueOf(bean.getDataOrdine()), dateMin, dateMax)) orderNew.add(bean);
				}
				request.removeAttribute("orders");
				request.setAttribute("orders", orderNew);
	        }
			else if(action.equalsIgnoreCase("orderDetails")) {
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
		
		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("orders");
			request.setAttribute("orders", orderDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error; " + e.getMessage());
		}
		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderHistory.jsp");
			dispatcher.forward(request, response);
	}


	private boolean isRightDate(Date date, Date dateMin, Date dateMax) 
	{
		if( date.compareTo(dateMin)==0 && date.compareTo(dateMin)>0 ) 
		{
			if( date.compareTo(dateMax)==0 && date.compareTo(dateMax)>0 ) return true;
		    else return false;
		} else return false;
	}

}
