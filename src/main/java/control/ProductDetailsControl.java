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

import java.util.Collection;

import model.bean.InfoBean;
import model.bean.ProductBean;
import model.bean.NutritionTableBean;
import model.dao.IInfoDao;
import model.dao.IProductDao;
import model.dao.INutritionTableDao;
import model.datasource.InfoDaoDataSource;
import model.datasource.ProductDaoDataSource;
import model.datasource.NutritionTableDaoDataSource;

/**
 * Servlet implementation class ProductDetailsControl
 */
@WebServlet("/ProductDetailsControl")
public class ProductDetailsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailsControl() {
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
		IProductDao prodDao = null;
		IInfoDao infoDao = null;
		INutritionTableDao nutDao= null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		prodDao= new ProductDaoDataSource(ds);
		infoDao= new InfoDaoDataSource(ds);
		nutDao = new NutritionTableDaoDataSource(ds);
		
		try {
			ProductBean product= prodDao.doRetrieveByKey(Integer.parseInt(request.getParameter("product"))) ;
			InfoBean info = infoDao.doRetrieveByKey(product.getCodice());
			NutritionTableBean nutritionTable = nutDao.doRetrieveByKey(product.getCodice());
			
			
			request.removeAttribute("product");
			request.setAttribute("product", product);
			request.removeAttribute("info");
			request.setAttribute("info", info);
			request.removeAttribute("nutritionTable");
			request.setAttribute("nutritionTable", nutritionTable);
			
		} catch(SQLException e) 
		{
			request.setAttribute("error",  "Error: c'è stato un problema con il recupero dati del prodotto.");
	 		response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productDetails.jsp");
		dispatcher.forward(request, response);
	}

}
