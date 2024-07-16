package control;

import com.google.gson.*;
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
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("ricerca");
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        IProductDao productDao = new ProductDaoDataSource(ds);

        try {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if(cart == null)
            {
                cart = new Cart();
                request.getSession().setAttribute("cart", cart);
            }
            
            System.out.println("Carrello" + cart);
            
            Collection<ProductBean> searchResults = productDao.searchByName(query);

            // Controllo se la richiesta è di tipo AJAX
            boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
            if (ajax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(searchResults.getClass(), new ProductBeanWithCartQuantity(cart))
                        .create();
                String json = gson.toJson(searchResults);
                response.getWriter().write(json);
            } else {
                request.setAttribute("searchResults", searchResults);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pages/searchResults.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider proper error handling
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static class ProductBeanWithCartQuantity implements JsonSerializer<Collection<ProductBean>> {
        private final Cart cart;
        public ProductBeanWithCartQuantity(Cart cart) {
            this.cart = cart;
        }

        @Override
        public JsonElement serialize(Collection<ProductBean> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray jsonArray = new JsonArray();

            for (ProductBean product : src) {
                JsonObject jsonObject = context.serialize(product).getAsJsonObject();
                jsonObject.addProperty("cartQuantity", cart.getProductQuantity(product));
                jsonArray.add(jsonObject);
            }

            return jsonArray;
        }
    }
}