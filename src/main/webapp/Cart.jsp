 <%@ page contentType="text/html; charset=UTF-8"
		  pageEncoding="UTF-8"%>

<% 
	Cart cart = (Cart) session.getAttribute("cart");
	System.out.println(cart);
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.*, model.Cart, javax.sql.DataSource, model.datasource.*"%>
<%@ page import="java.sql.SQLException" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="productStyle.css" rel="stylesheet" type="text/css">
	<title>Carrello</title>
</head>

<body>
  <%InfoDaoDataSource DAOEmily = new InfoDaoDataSource((DataSource)request.getServletContext().getAttribute("DataSource"));
	  if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<% List<ProductBean> prodcart = (List<ProductBean>) cart.getProducts();
		   for(ProductBean beancart: prodcart) {
               InfoBean infob = null;
               try {
                   infob = DAOEmily.doRetrieveByKey(beancart.getCodice());
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
        %>
		<tr>
			<td><%=infob.getNome()%></td>
			<td><%=infob.getCosto()%></td>
			<td><a href="cartControl?action=deleteC&id=<%=beancart.getCodice()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	
	
  <a href="loginView.jsp" > Login</a>
  <a href="RegisterView.jsp" > Register</a>
   <a href="CartView.jsp" > Cart</a>
   <a href="Checkout.jsp" > Checkout</a>
   <a href="Product.jsp" > Product</a> <!-- I piedi di Ste -->
</body>
</html>