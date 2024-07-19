<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="assets/styles/login.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
<div class="login">
<h1> Login </h1>
  <form action="login" method="post">
  	 <div class="textbox">
  	 <!--<label for="name"> Username: </label><br>-->
  	 <input name="username" type="text" maxlength="30" required placeholder="enter username"><br>
	 </div>
		 <div class="textbox">
		 <!--<label for="name"> Password: </label><br>-->
         <input name="password" type="password" maxlength="30" required placeholder="enter password"><br>
       </div>
        <div class="invio">
             <input type="submit" value="Login">
        </div>
      <div class="rightalign">
      </div>
	  <input type="reset" value="Reset">
    </form>
    <a href="RegisterView.jsp" > register</a>
</div>
</body>
</html>