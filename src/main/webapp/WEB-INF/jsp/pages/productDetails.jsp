<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./errorPage.jsp"%>

<% 
	ImageDaoDataSource imageDao = new ImageDaoDataSource( (DataSource) request.getServletContext().getAttribute("DataSource"));
    InfoDaoDataSource infoDao = new InfoDaoDataSource( (DataSource) request.getServletContext().getAttribute("DataSource"));
	ProductBean product = (ProductBean) request.getAttribute("product");
	InfoBean info = (InfoBean) request.getAttribute("info");
	NutritionTableBean nut = (NutritionTableBean) request.getAttribute("nutritionTable");
	if(product == null) {
		response.sendRedirect("./details");
		return;
	}
	Collection<?> images = (Collection<?>) product.getImages(); 
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.ProductBean, model.bean.*, model.Cart.*, model.datasource.*, javax.sql.DataSource"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Merriweather+Sans&display=swap" rel="stylesheet">
	<link href="product.css" rel="stylesheet" type="text/css">
	<title><%=product.getNome()%></title>
</head>

<body>

  <h2><%=product.getNome()%></h2>
  
  	    <% Iterator<?> it = images.iterator();
  	    while (it.hasNext()) { 
  	    ImageBean img = (ImageBean) it.next(); %>
  		<img src="assets/images/products/<%=img.getImg()%>" alt="product_img" width="300" height="300">
  		<% } %> 
  		<br>
  		<p> <strong>Tipologia: </strong> <%=info.getTipologia()%></p>
   		 <p> <strong>Costo: </strong><%=info.getCosto()%> €</p>
   		 <p> <strong>Descrizione </strong><br><%=info.getDescrizione()%></p>

<% if(info.getTipologia().equals("altro")==false)
{ %>
<h2> Valore Nutrizionale</h2>
 <table border="1">
  	<tr>
  	    <th>Valore Nutrizionale</th>  
  		<th>Quantità</th>	
  	</tr>
  	<tr>
  		<td>energia</td>
  		<td><%=nut.getEnergia()%> kl</td>
  	</tr>
  	<tr>
  		<td>grassi</td>
  		<td><%=nut.getGrassi()%> g</td>
  	</tr>
  	<tr>
  		<td>di cui saturi</td>
  		<td><%=nut.getGrassiSaturi()%> g</td>
  	</tr>
  	<tr>
  		<td>carboedrati</td>
  		<td><%=nut.getCarboedrati()%> g</td>
  	</tr>
  	<tr>
  		<td>zucherri</td>
  		<td><%=nut.getZucherri()%> g</td>
  	</tr>
  	<tr>
  		<td>fibre</td>
  		<td><%=nut.getFibre()%> g</td>
  	</tr>
  	<tr>
  		<td>proteine</td>
  		<td><%=nut.getProteine()%> g</td>
  	</tr>
  	<tr>
  		<td>sale</td>
  		<td><%=nut.getSale()%> g</td>
  	</tr>

  </table>

<%} %>
   <a href="cartControl?action=addC&id=<%=product.getCodice()%>"> Add to cart</a><br>
  <a href="loginView.jsp" > Login </a>
  <a href="RegisterView.jsp" > Register </a>
	<a href="Cart.jsp" > Cart </a>
   <a href="WEB-INF/jsp/pages/Checkout.jsp" > Checkout </a>
	<a href="WEB-INF/jsp/admin/ProductAdmin.jsp" > Admin </a>
	<a href="WEB-INF/jsp/pages/OrderHistory.jsp" > Order History </a>
</body>
</html>