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

import model.Cart;
import model.dao.IProductDao;
import model.datasource.ProductDaoDataSource;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
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
		
		
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		productDao = new ProductDaoDataSource(ds);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null) 
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
	
		try {
	    	String action = request.getParameter("action");
	        if(action.equalsIgnoreCase("DeleteC")) {
		    int id = Integer.parseInt(request.getParameter("id"));
		    cart.deleteProduct(productDao.doRetrieveByKey(id));
	        }
		} catch(SQLException e) {
			System.out.println("Error; " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
		dispatcher.forward(request, response);		

	}
}
