package control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener 
{
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		//Per usare il Data  Source
		DataSource ds = null; try	{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
		

		context.setAttribute("DataSource", ds);
		System.out.println("DataSource creation...."+ds.toString());
		
		// Per usare il DriverManager
		/*DriverManagerConnectionPool dm = new DriverManagerConnectionPool();
		context.setAttribute("DriverManager", dm);
		System.out.println("DriverManager creation...."+dm.toString());		*/
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
