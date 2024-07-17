<%--
  Created by IntelliJ IDEA.
  User: luigi
  Date: 17/07/2024
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    UserBean user = (UserBean) request.getSession().getAttribute("loggedUser");
    Collection<?> userOrders = (Collection<?>) request.getAttribute("loggedUserOrders");
    if(userOrders == null) {
        response.sendRedirect("./userprofile");
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
    <title>User Profile</title>
</head>

<body>
<%
    if(session.getAttribute("LoggedUser")!=null)
    { %>
<h1> Sei Loggato!</h1>
<% } %>

<h2> Profilo Utente </h2>
<h4>Username</h4><a><%=user.getUsername()%></a>
<h4>Email</h4><a><%=user.getEmail()%></a>
<h4>Nome e Cognome</h4><a><%=user.getNomeCognome()%></a>
<h4>Data di Nascita</h4><a><%=user.getDataNascita()%></a>
<h4>Sesso</h4><a><%=user.getSesso()%></a>
<h4>Paese</h4><a><%=user.getPaese()%></a>
<h2> I tuoi ordini </h2>
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
        if(userOrders != null && userOrders.size()!= 0) {
            Iterator<?> it = userOrders.iterator();
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
            <a href="UserProfile?action=orderDetails&orderUser=<%=bean.getUtente()%>&orderNum=<%=bean.getCodice()%>"> Details</a><br>
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