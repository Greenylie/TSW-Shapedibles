<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>


<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link href="productStyle.css" rel="stylesheet" type="text/css">
   <title>pagina di errore</title>
</head>
	
<body>
 <h1> C'è stato un errore!</h1>
 <% 
		String error = (String) request.getAttribute("error");
 		if(error==null || error.isEmpty()){
 %>
 <a> Prego riprovare più tardi</a> 
 <% } else { %> 
 <a><%=error%></a> <br>
 <a> Prego riprovare più tardi</a> 
 <% } %>

</body>
</html>