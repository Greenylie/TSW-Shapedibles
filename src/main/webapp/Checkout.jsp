<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	UserBean user= new UserBean();
 	user = (UserBean) request.getSession().getAttribute("LoggedUser");
 	
	if(user == null) {
		request.setAttribute("Checkout", "Si");
		response.sendRedirect(request.getContextPath() + "/loginView.jsp");
	}  %>
	
    
<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.UserBean, javax.servlet.http.HttpSession"%>

<head>
<meta charset="UTF-8">
<title>Checkout</title>
  
</head>
<body>
 	<h2>Checkout </h2>
   <h3> IL tuo acquisto e stato completato!</h3>
</body>
</html>