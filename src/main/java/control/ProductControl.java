package control;

import model.Cart;
import model.bean.ProductBean;
import model.dao.IProductDao;
import model.datasource.ProductDaoDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ProductControl
 */
@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductControl() {
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
			} else if(action.equalsIgnoreCase("insert")) {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				Double price= Double.parseDouble(request.getParameter("price"));
				int quantity= Integer.parseInt(request.getParameter("quantity"));
				
				ProductBean bean = new ProductBean();
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setCosto(price);
				bean.setDisponibilità(quantity);
				productDao.doSave(bean);
			}
		}
		
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", productDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error; " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Product.jsp");
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
