package control;

import com.google.gson.Gson;
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
import java.io.Serial;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ProductControl
 */
@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductControl() {
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
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		Map<String, Object> responseData = new HashMap<>();
		IProductDao productDao;
		
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
				} else if(action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(productDao.doRetrieveByKey(id));
				} else if(action.equalsIgnoreCase("read")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", productDao.doRetrieveByKey(id));
				} else if(action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					productDao.doDelete(id);
				} else if(action.equalsIgnoreCase("insert")) {
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price= Double.parseDouble(request.getParameter("price"));
					int quantity= Integer.parseInt(request.getParameter("quantity"));

					ProductBean bean = new ProductBean();
					bean.setNome(name);
					bean.setDescrizione(description);
					bean.setCosto(price);
					bean.setDisponibilità(quantity);
					productDao.doSave(bean);
				}

				responseData.put("cartSize", cart.getCartSize());

				request.getSession().setAttribute("cart", cart);
				String sort = request.getParameter("sort");

				request.removeAttribute("products");
				request.setAttribute("products", productDao.doRetrieveAll(sort));
			}

		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		if (ajax) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = new Gson().toJson(responseData);
            response.getWriter().write(json);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/TestDAO.jsp");
			dispatcher.forward(request, response);
		}
	}

}
