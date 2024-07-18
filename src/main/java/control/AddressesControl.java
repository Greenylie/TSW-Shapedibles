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

import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.IAddressDao;
import model.dao.IUserDao;
import model.datasource.AddressDaoDataSource;
import model.datasource.UserDaoDataSource;

/**
 * Servlet implementation class AdressesControl
 */
@WebServlet("/addressesControl")
public class AddressesControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressesControl() {
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
		UserBean user = (UserBean) request.getSession().getAttribute("LoggedUser");
		IUserDao userDao = null;
		IAddressDao addressDao= null;
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
		userDao = new UserDaoDataSource(ds);
		addressDao= new AddressDaoDataSource(ds);
		
		 int max = 50;
	     int min = 1;
	     int range = max - min + 1;
	     int number= (int) (Math.random() * range) - min;
		
		String action = request.getParameter("action");
		
		try {
			if(action != null){
				if(action.equalsIgnoreCase("Add")) {
					AddressBean address = new AddressBean();
					address.setId("ad" + user.getUsername() +"-" + number);
					address.setUtente(user.getUsername());
					address.setPaese(request.getParameter("paese"));
					address.setCittà(request.getParameter("citta"));
					address.setStrada(request.getParameter("strada"));
					address.setNumero(Integer.parseInt(request.getParameter("numero")));
					address.setCodicePostale(request.getParameter("cap"));
					
					addressDao.doSave(address);
				} else if(action.equalsIgnoreCase("delete")) {
					String id = request.getParameter("id");
					addressDao.doDelete(id, user.getUsername());
				}
			}
			
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		
		
		try {
			request.removeAttribute("addresses");
			request.setAttribute("addresses", addressDao.doRetrieveByUser(user.getUsername()) );
		} catch (SQLException e) {
			System.out.println("Error; " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Adresses.jsp");
		dispatcher.forward(request, response);
	}

}
