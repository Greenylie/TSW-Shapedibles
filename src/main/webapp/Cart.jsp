 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.ProductBean, model.Cart"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="productStyle.css" rel="stylesheet" type="text/css">
	<title>Carrello</title>
</head>

<body>
  <% if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Action</th>
		</tr>
		<% List<ProductBean> prodcart = cart.getProducts(); 	
		   for(ProductBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getNome()%></td>
			<td><a href="product?action=deleteC&id=<%=beancart.getCodice()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	
	
  <a href="loginView.jsp" > login</a>
  <a href="RegisterView.jsp" > register</a>
   <a href="CartView.jsp" > Cart</a>
   <a href="Checkout.jsp" > Checkout</a>
</body>
</html>