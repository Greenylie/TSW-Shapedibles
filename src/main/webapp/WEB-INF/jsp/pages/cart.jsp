 <%@ page contentType="text/html; charset=UTF-8"
		  pageEncoding="UTF-8" errorPage="errorPage.jsp"%>

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
	<jsp:include page="../procedural/fractalNoise.jsp"/>
	<link href="${pageContext.request.contextPath}/assets/styles/base.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/styles/pages/cart.css" rel="stylesheet" type="text/css">
	<title>Carrello</title>
</head>

<body>
<jsp:include page="../common/sticky.jsp"/>
<div class="content">
	<div class="base glassy">
	<h2>Cart</h2>
  <%InfoDaoDataSource DAOEmily = new InfoDaoDataSource((DataSource)request.getServletContext().getAttribute("DataSource"));
	  if(cart != null) { %>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
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
			<td><%=cart.getProductQuantity(beancart)%></td>
			<td><a href="cartControl?action=deleteC&id=<%=beancart.getCodice()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>
	</div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>