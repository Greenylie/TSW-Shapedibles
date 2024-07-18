<%--
  Created by IntelliJ IDEA.
  User: luigi
  Date: 17/07/2024
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    Collection<?> users = (Collection<?>) request.getAttribute("users");
    if(users == null) {
        response.sendRedirect("./users");
        return;
    }
%>

<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.bean.ProductBean, model.bean.*, model.Cart.*, model.datasource.*, javax.sql.DataSource"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="assets/styles/product.css" rel="stylesheet" type="text/css">
    <title>Gestione Account</title>
</head>

<body>
<div class="productContainer">
    <%
        if(session.getAttribute("LoggedUser")!=null)
        { %>
    <h1> Sei Loggato!</h1>
    <% } %>

    <h2> Account </h2>
    <a href="product">List</a>
    <table border="1">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Sesso</th>
            <th>Nome e Cognome</th>
            <th>Data di nascita</th>
            <th>Paese</th>
            <th>Admin</th>
        </tr>
        <%
            if(users != null && users.size()!= 0) {
                Iterator<?> it = users.iterator();
                while (it.hasNext()) {
                    UserBean bean = (UserBean) it.next();
        %>
        <tr>
            <td><%=bean.getUsername()%></td>
            <td><%=bean.getEmail()%></td>
            <td><%=bean.getSesso()%></td>
            <td><%=bean.getNomeCognome()%></td>
            <td><%=bean.getDataNascita()%></td>
            <td><%=bean.getPaese()%></td>
            <td><%=bean.getUserAdmin()%></td>
            <td>
                <a href="users?action=admin&username=<%=bean.getUsername()%>">Make Admin</a><br>
                <a href="users?action=delete&username=<%=bean.getUsername()%>">Delete user</a><br>
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

    <a href="loginView.jsp" > Login </a>
    <a href="RegisterView.jsp" > Register </a>
    <a href="Cart.jsp" > Cart </a>
    <a href="Checkout.jsp" > Checkout </a>
    <a href="ProductAdmin.jsp" > Admin </a>
    <a href="OrderHistory.jsp" > Order History </a>
</div>
</body>
</html>
