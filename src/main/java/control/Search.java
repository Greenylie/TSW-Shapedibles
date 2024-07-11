package control;

import com.google.gson.Gson;
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
import java.util.List;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("ricerca");
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        IProductDao productDao = new ProductDaoDataSource(ds);

        try {
            List<ProductBean> searchResults = productDao.searchByName(query);

            // Controllo se la richiesta è di tipo AJAX
            boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
            if (ajax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String json = new Gson().toJson(searchResults);
                response.getWriter().write(json);
            } else {
                request.setAttribute("searchResults", searchResults);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/searchResults.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider proper error handling
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}