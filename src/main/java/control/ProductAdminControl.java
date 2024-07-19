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
 * Servlet implementation class ProductAdminControll
 */
@WebServlet("/ProductAdminControl")
public class ProductAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String action = request.getParameter("action");
		
		try {
		if(action != null){
			
			if(action.equalsIgnoreCase("addC")) {
				int id = Integer.parseInt(request.getParameter("id"));
				cart.addProduct(productDao.doRetrieveByKey(id));
			}  else if(action.equalsIgnoreCase("read")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				request.setAttribute("product", productDao.doRetrieveByKey(id));
			} else if(action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				productDao.doDelete(id);
			}
		}
		
		} catch(SQLException e) {
			request.setAttribute("error",  "Error: sembra esserci un problema con l'elaborazione deei prodotti.");
	 		response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());

		}
		
		request.getSession().setAttribute("cart", cart);
		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", productDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			request.setAttribute("error",  "Error: c'è stato un problema con il recupero dati dei prodotti.");
	 		response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());

		}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductAdmin.jsp");
			dispatcher.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
