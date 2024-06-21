 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.UserBean, control.UserDaoDataSource, java.security.NoSuchAlgorithmException, control.DriverManagerConnectionPool, java.sql.SQLException, java.util.*, javax.sql.DataSource, javax.servlet.ServletContext" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina di test</title>
</head>
<body>
  <h1> Pagina di test del DAO </h1>
  <%
      ServletContext context = request.getServletContext();
      DataSource dmcp= (DataSource) context.getAttribute("DataSource");
	  UserBean user= new UserBean();
	  UserDaoDataSource userdao = new UserDaoDataSource(dmcp);
	  
	  UserBean user1=new UserBean();
	  UserDaoDataSource userdao1 = new UserDaoDataSource(dmcp);
	  
	  try{
	  user.setUsername("admin2");
	  user.setEmail("TryAdmin2@gmail.com"); 
	  user.setPass("admin2");
	  user.setNomeCognome("Mera Salamin");
	  user.setSesso("Donna");
	  user.setPaese("Italia");
	  user.setDataNascita("2024-06-17");
	  user.setUserAdmin(1);
	  
	  /* user1.setUsername("user2");
	  user1.setEmail("TryUser2@gmail.com"); 
	  user1.setPass("user2");
	  user1.setNomeCognome("Indus Tarbella");
	  user1.setSesso("uomo");
	  user1.setPaese("Italia");
	  user1.setDataNascita("2024-06-17");
	  user1.setUserAdmin(0); */
	  
     userdao.doSave(user); 
	  //userdao1.doSave(user1);
	  
	  
	  } catch(NoSuchAlgorithmException e)
	   { System.out.println("Eccezione cifratura");} 
	   catch(SQLException e)
	   { System.out.println("Eccezione SQL");
	   e.printStackTrace();}  
	  
	  
  %>
</body>
</html>