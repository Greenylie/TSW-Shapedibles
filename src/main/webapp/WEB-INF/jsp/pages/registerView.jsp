<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/pages/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/assets/styles/pages/register.css" rel="stylesheet" type="text/css">
<title>Registrazione</title>
</head>
<body>
<section class="registerBase">
	<div class="mediaRegister">
<h2> Register</h2>
  <form action="register" method="post" class="form">
	  <div class="input-box">
  	 <label for="username"> Username: </label><br>
  	 <input name="username" type="text" maxlength="30" required placeholder="enter Username"><br>
	  </div>
	  <div class="input-box">
  	  <label for="email"> Email: </label><br>
  	 <input name="email" type="email" maxlength="80" required placeholder="enter email"><br>
	  </div>
	  <div class="input-box">
  	 <label for="password"> Password: </label><br>
  	 <input name="password" type="password" maxlength="30" required placeholder="enter password"><br>
	  </div>
	  <div class="input-box">
  	  <label for="passwordConf"> Repeat the password: </label><br>
  	 <input name="passwordConf" type="password" maxlength="30" required placeholder="enter password again"><br>
	  </div>
	  <div class="input-box">
  	 <label for="nome e cognome"> Nome e cognome: </label><br>
  	 <input name="nome_cognome" type="text" maxlength="30" required placeholder="enter name and surname"><br>
	  </div>
	  <div class="input-box">
  	 <label for="sesso"> Sesso: </label><br>
  	 <input name="sesso" type="text" maxlength="30" required placeholder="enter s"><br>
	  </div>
	  <div class="input-box">
  	  <label for="paese"> Paese: </label><br>
  	 <input name="paese" type="text" maxlength="30" required placeholder="enter "><br>
	  </div>
	  <div class="input-box">
  	 <label for="data di Nascità"> Data di Nascità: </label><br>
  	 <input name="data_nascita" type="date" maxlength="30" required placeholder="enter email"><br>
	  </div>
  	 <input type="submit" value="Register"> <input type="reset" value="Reset">
  </form>
<br>
	<hr>
	<br>
<div class="login">
  <a href="${pageContext.request.contextPath}/login" > login</a>
</div>
	</div>
</section>
</body>
</html>