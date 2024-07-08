 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");
		return;
	}
	ProductBean product = (ProductBean) request.getAttribute("product");
	Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.ProductBean, model.Cart"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="productStyle.css" rel="stylesheet" type="text/css">
	<title>Pagina prodotti  </title>
</head>

<body>
	<% 
	if(session.getAttribute("LoggedUser")!=null)
	{ %>
	  <h1> Sei Loggato!</h1>
	<% } %>

  <h2> Products </h2>
  <a href="product">List</a>
  <table border="1">
   <tr>
   		<th>Code <a href="product?sort=codice"> Sort</a></th>
   		<th>Name <a href="product?sort=nome"> Sort</a></th>
   		<th>Description <a href="product?sort=descrizione"> Sort</a></th>
   		<th>Action</th>
   </tr>
  <%
	if(products != null && products.size()!= 0) {
		Iterator<?> it = products.iterator();
		while (it.hasNext()) {
			ProductBean bean = (ProductBean) it.next();
  %>
  <tr>
   		<td><%=bean.getCodice()%></td>
   		<td><%=bean.getNome()%></td>
   		<td><%=bean.getDescrizione()%></td>
   		<td> <a href="product?action=delete&id=<%=bean.getCodice()%>"> Delete</a><br>
   			 <a href="product?action=read&id=<%=bean.getCodice()%>"> Details</a><br>
   			 <a href="product?action=addC&id=<%=bean.getCodice()%>"> Add to cart</a><br>
   		</td>
   </tr>
   <% 
	  }
	  } else {
   %>
    <tr>
   		<td colspan="6"> No products available</td>
   </tr>
    <% 
	  }
   %>
  </table>
  
  <h2>Dettagli</h2>
  <% 
  	if (product != null) {
  %>
  <table border="1">
  	<tr>
  		<th>Code</th>
  		<th>Name</th>
  		<th>Description</th>
  		<th>Price</th>
  		<th>Quantity</th>  
  	</tr>
  	<tr>
  		<td><%=product.getCodice()%></td>
   		<td><%=product.getNome()%></td>
   		<td><%=product.getDescrizione()%></td>
   		<td><%=product.getCosto()%></td>
   		<td><%=product.getDisponibilità()%></td>
  	</tr>
  </table>
  <%
  	}
  %>
  
  <h2>Insert</h2>
  <form action="product" method="post">
  	<input type="hidden" name="action" value="insert">
  	
  	<label for="name">Name:</label><br>
  	<input name="name" type="text" maxlength="20" required placeholder="enter name"><br>
  	
  	<label for="description">Description:</label><br>
  	<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
  	
  	<label for="price">Price:</label><br>
  	<input name="price" type="number" step="0.01" min="0.00" value="0.0" required><br>
  	
  	<label for="quantity">Quantity:</label><br>
  	<input name="quantity" type="number" min="1" value="1" required><br>	
  	
  	<input type="submit" value="Add"><input type="reset" value="Reset">  	
  </form>
  
  <a href="loginView.jsp" > login</a>
  <a href="RegisterView.jsp" > register</a>
   <a href="CartView.jsp" > Cart</a>
   <a href="Checkout.jsp" > Checkout</a>
   
</body>
</html>