<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="assets/styles/register.css" rel="stylesheet" type="text/css">
<title>Registrazione</title>
</head>
<body>

<h2> Register</h2>
  <form action="register" method="post"> 
  	 
  	 <label for="username"> Username: </label><br>
  	 <input name="username" type="text" maxlength="30" required placeholder="enter Username"><br>
  	 
  	  <label for="email"> Email: </label><br>
  	 <input name="email" type="email" maxlength="80" required placeholder="enter email"><br>
  	 
  	 <label for="password"> Password: </label><br>
  	 <input name="password" type="password" maxlength="30" required placeholder="enter password"><br>
  	 
  	  <label for="passwordConf"> Repeat the password: </label><br>
  	 <input name="passwordConf" type="password" maxlength="30" required placeholder="enter password again"><br>
  	 
  	 <label for="nome e cognome"> Nome e cognome: </label><br>
  	 <input name="nome_cognome" type="text" maxlength="30" required placeholder="enter name and surname"><br>
  	 
  	 <label for="sesso"> Sesso: </label><br>
  	 <input name="sesso" type="text" maxlength="30" required placeholder="enter s"><br>
  	 
  	  <label for="paese"> Paese: </label><br>
  	 <input name="paese" type="text" maxlength="30" required placeholder="enter "><br>
  	 
  	 <label for="data di Nascità"> Data di Nascità: </label><br>
  	 <input name="data_nascita" type="date" maxlength="30" required placeholder="enter email"><br>
  	 
  	 <input type="submit" value="Register"> <input type="reset" value="Reset">
  </form>
  
  <a href="loginView.jsp" > login</a>
</body>
</html>