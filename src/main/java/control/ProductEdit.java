package control;

import model.bean.InfoBean;
import model.bean.NutritionTableBean;
import model.bean.ProductBean;
import model.dao.IInfoDao;
import model.dao.INutritionTableDao;
import model.dao.IProductDao;
import model.datasource.InfoDaoDataSource;
import model.datasource.NutritionTableDaoDataSource;
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
 * Servlet implementation class ProductEdit
 */
@WebServlet("/ProductEdit")
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEdit() {
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
		
		InfoBean beanIn = new InfoBean();
		NutritionTableBean beanNT = new NutritionTableBean();
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
			
			String action = request.getParameter("action");
			
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
				beanIn.setCodice(product.getCodice());
				beanIn.setNome(request.getParameter("name"));
				beanIn.setDescrizione(request.getParameter("description"));
				beanIn.setCosto(Double.parseDouble(request.getParameter("price")));
				beanIn.setDisponibilità(Integer.parseInt(request.getParameter("quantity")));
				beanIn.setTipologia(request.getParameter("type"));
				beanNT.setCodiceProdotto(product.getCodice());
				beanNT.setEnergia(Integer.parseInt(request.getParameter("energy")));
				beanNT.setGrassi(Double.parseDouble(request.getParameter("fat")));
				beanNT.setGrassiSaturi(Double.parseDouble(request.getParameter("saturedFats")));
				beanNT.setCarboedrati(Double.parseDouble(request.getParameter("carbs")));
				beanNT.setZucherri(Double.parseDouble(request.getParameter("sugars")));
				beanNT.setFibre(Double.parseDouble(request.getParameter("fibers")));
				beanNT.setProteine(Double.parseDouble(request.getParameter("proteins")));
				beanNT.setSale(Double.parseDouble(request.getParameter("salt")));
				
				infoDao.doSave(beanIn);
				
				nutDao.doDelete(product.getCodice());
				
				prodDao.doUpdateInfo( product.getCodice(), infoDao.doRetrieveByName(product.getNome()).getCodice());
				
				nutDao.doSave(beanNT);
				
				}
			}	
		} catch(SQLException e) 
		{
			request.setAttribute("error",  "Error: c'è stato un problema nella modifica dei dati del prodotto.");
	 		response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productDetails.jsp");
		dispatcher.forward(request, response);
	}

}
