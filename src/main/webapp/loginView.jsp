<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
  <h2> Login </h2>
  <form action="login" method="post"> 
  	 
  	 <label for="name"> Username: </label><br>
  	 <input name="username" type="text" maxlength="30" required placeholder="entre username"><br>
  	 
  	 <label for="name"> Password: </label><br>
  	 <input name="password" type="text" maxlength="30" required placeholder="enter password"><br>
  	 
  	 <input type="submit" value="Login"> <input type="reset" value="Reset">  
  	 
  	  <a href="RegisterView.jsp" > register</a>	
  </form>
  
   <a href="RegisterView.jsp" > register</a>
</body>
</html>