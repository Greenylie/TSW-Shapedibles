package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Cart;
import model.bean.AddressBean;
import model.bean.ContainBean;
import model.bean.InfoBean;
import model.bean.OrderBean;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.dao.IAddressDao;
import model.dao.IContainDao;
import model.dao.IImageDao;
import model.dao.IInfoDao;
import model.dao.IOrderDao;
import model.dao.IProductDao;
import model.datasource.AddressDaoDataSource;
import model.datasource.ContainDaoDataSource;
import model.datasource.InfoDaoDataSource;
import model.datasource.OrderDaoDataSource;
import model.datasource.ProductDaoDataSource;

/**
 * Servlet implementation class checkoutControl
 */
@WebServlet("/checkoutControl")
public class checkoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	LocalDateTime now = LocalDateTime.now();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkoutControl() {
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
		IProductDao productDao = null;
		IOrderDao orderDao = null;
		IAddressDao addressDao = null;
		IInfoDao infoDao= null;
		IContainDao containDao = null;
		
	
		OrderBean bean = new OrderBean();
		ContainBean contain= new ContainBean();
		UserBean user= new UserBean();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
	 	user = (UserBean) request.getSession().getAttribute("LoggedUser");
	 	String action = request.getParameter("action");

		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");		
		productDao = new ProductDaoDataSource(ds);
		orderDao = new OrderDaoDataSource(ds);
		addressDao = new AddressDaoDataSource(ds);
		infoDao = new InfoDaoDataSource(ds);
		containDao = new ContainDaoDataSource(ds); 
		
				
		try {
			
			 Collection<AddressBean> addresses = addressDao.doRetrieveByUser(user.getUsername());
			
			 request.removeAttribute("addresses");
			 request.setAttribute("addresses", addresses);

			
				bean.setCodice((int) Math.random());
				bean.setUtente(user.getUsername());
				bean.setStato("in checkout");
				bean.setDataOrdine(now.toString());
				
	 	
				Double saldotot=0.0;
	 	
				List<ProductBean> prodcart = cart.getProducts();
				List<ContainBean> containList = new ArrayList<>();
					for(ProductBean beancart: prodcart) {
							InfoBean infob = infoDao.doRetrieveByKey(beancart.getCodice());
							saldotot += infob.getCosto();
							contain.setCodiceProdotto(beancart.getCodice());
							contain.setCodiceOrdine(bean.getCodice());
							containList.add(contain);
					}
				bean.setSaldoTotale(saldotot);	
				request.removeAttribute("order");
				request.setAttribute("order", bean);
			
		   
				if(action != null){
					if(action.equalsIgnoreCase("addressSelect")) {
						AddressBean address = (AddressBean) request.getSession().getAttribute("adress");
						bean.setIndirizzo(address.getId());
						request.removeAttribute("order");
						request.setAttribute("order", bean);
					} else if(action.equalsIgnoreCase("confirm")) {
						bean.setStato("completato"); 
						orderDao.doSave(bean);	
						for(ContainBean conbean: containList) {
							containDao.doSave(conbean);
						}
		        }
			  }
		   
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Checkout.jsp");
		   dispatcher.forward(request, response);
		  
		   
	 	} catch(SQLException e) 
	 	{
	 		System.out.println("Error: " + e.getMessage());
	 	}
		   
	 	
	}

}
