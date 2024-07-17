package control;

import model.Cart;
import model.bean.*;
import model.dao.*;
import model.datasource.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Servlet implementation class checkoutControl
 */
@WebServlet("/checkoutControl")
public class checkoutControl extends HttpServlet {
	@Serial
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
		OrderBean bean = new OrderBean();
		ContainBean contain= new ContainBean();
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        UserBean user = (UserBean) request.getSession().getAttribute("LoggedUser");
	 	String action = request.getParameter("action");

		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
        IProductDao productDao = new ProductDaoDataSource(ds);
        IOrderDao orderDao = new OrderDaoDataSource(ds);
        IAddressDao addressDao = new AddressDaoDataSource(ds);
        IInfoDao infoDao = new InfoDaoDataSource(ds);
        IContainDao containDao = new ContainDaoDataSource(ds); 
		
				
		try {
			
			 Collection<AddressBean> addresses = addressDao.doRetrieveByUser(user.getUsername());
			
			 request.removeAttribute("addresses");
			 request.setAttribute("addresses", addresses);

			
				bean.setCodice((int) Math.random());
				bean.setUtente(user.getUsername());
				bean.setStato("in checkout");
				bean.setDataOrdine(now.toString());
				
	 	
				double saldotot=0.0;
	 	
				List<ProductBean> prodcart = (List<ProductBean>) cart.getProducts();
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
