package control;

import model.bean.ContainBean;
import model.bean.OrderBean;
import model.dao.IContainDao;
import model.dao.IOrderDao;
import model.datasource.ContainDaoDataSource;
import model.datasource.OrderDaoDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/Orders")
public class Orders extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDateTime now = LocalDateTime.now();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IOrderDao orderDao = null;
		IContainDao containDao = null;
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		orderDao = new OrderDaoDataSource(ds);
		containDao = new ContainDaoDataSource(ds);
		String action = request.getParameter("action");
		
	
		try {
			String sort = request.getParameter("sort");
	
			request.removeAttribute("orders");
			request.setAttribute("orders", orderDao.doRetrieveAll(sort)); 

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
				Collection<ContainBean> items = containDao.doRetrieveByOrder(orderNum);
				request.removeAttribute("Details");
				request.setAttribute("Details", items);
			}
		}
		} catch(SQLException e) {
			request.setAttribute("error",  "Error: c'è stato un errore nel elaborazione degli ordini.");
	 		response.sendError(500, "Error: " + e.getMessage());
		}
		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin/orderHistory.jsp");
			dispatcher.forward(request, response);
	}


	private boolean isRightDate(Date date, Date dateMin, Date dateMax) 
	{
		if( date.compareTo(dateMin)==0 || date.compareTo(dateMin)>0 ) 
		{
			if( date.compareTo(dateMax)==0 || date.compareTo(dateMax)<0 ) return true;
		    else return false;
		} else return false;
	}

}
