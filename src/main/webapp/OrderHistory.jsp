<%--
  Created by IntelliJ IDEA.
  User: luigi
  Date: 15/07/2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    Collection<?> orders = (Collection<?>) request.getAttribute("orders");
    if(orders == null) {
        response.sendRedirect("./orders");
        return;
    }
    Collection<ContainBean> items = (Collection<ContainBean>) request.getAttribute("Details");
    InfoDaoDataSource infoDao= new InfoDaoDataSource((DataSource) request.getAttribute("DataSource"));
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.ProductBean, model.bean.*, model.Cart.*, model.datasource.*, javax.sql.DataSource"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="productStyle.css" rel="stylesheet" type="text/css">
    <title>Order History</title>
</head>

<body>
<%
    if(session.getAttribute("LoggedUser")!=null)
    { %>
<h1> Sei Loggato!</h1>
<% } %>

<h2> Ordini </h2>
<a href="product">List</a>
<table border="1">
    <tr>
        <th>Utente</th>
        <th>Code</th>
        <th>Stato</th>
        <th>Data</th>
        <th>Saldo</th>
        <th>Action</th>
    </tr>
    <%
        if(orders != null && orders.size()!= 0) {
            Iterator<?> it = orders.iterator();
            while (it.hasNext()) {
                OrderBean bean = (OrderBean) it.next();
    %>
    <tr>
        <td><%=bean.getUtente()%></td>
        <td><%=bean.getCodice()%></td>
        <td><%=bean.getDataOrdine()%></td>
        <td><%=bean.getStato()%></td>
        <td><%=bean.getSaldoTotale()%></td>
        <td>
            <a href="orders?action=orderDetails&orderUser=<%=bean.getUtente()%>&orderNum=<%=bean.getCodice()%>"> Details</a><br>
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
    if(items != null && items.size()!= 0) {
        Iterator<?> it = items.iterator();
        while (it.hasNext()) {
            ContainBean bean = (ContainBean) it.next();
        InfoBean info = infoDao.doRetrieveByKey(bean.getCodiceProdotto());

%>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th>

    </tr>
    <tr>
        <td><%=info.getNome()%></td>
        <td><%=info.getDescrizione()%></td>
        <td><%=info.getCosto()%></td>
        <td><%=info.getDisponibilità()%></td>
    </tr>
</table>
<%
    }}
%>

<a href="loginView.jsp" > Login </a>
<a href="RegisterView.jsp" > Register </a>
<a href="Cart.jsp" > Cart </a>
<a href="Checkout.jsp" > Checkout </a>
<a href="ProductAdmin.jsp" > Admin </a>

</body>
</html>