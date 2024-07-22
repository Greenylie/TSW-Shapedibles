package control;

import model.dao.IInfoDao;
import model.dao.INutritionTableDao;
import model.dao.IProductDao;
import model.datasource.InfoDaoDataSource;
import model.datasource.NutritionTableDaoDataSource;
import model.datasource.ProductDaoDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

public class Home extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IProductDao prodDao;
        IInfoDao infoDao = null;
        INutritionTableDao nutDao= null;
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

        prodDao= new ProductDaoDataSource(ds);
        infoDao= new InfoDaoDataSource(ds);
        nutDao = new NutritionTableDaoDataSource(ds);

        try {
            request.setAttribute("products", prodDao.doRetrieveAll("codice"));
        } catch (SQLException e) {
            request.setAttribute("error",  "Error: c'è stato un problema con il recupero dati dei prodotti.");
            response.sendError(500, "Error: " + e.getMessage());System.out.println("Error..." + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/jsp/pages/home.jsp").forward(request, response);
    }
}
