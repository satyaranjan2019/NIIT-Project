<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ include file="HeaderForShopkart.jsp" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <title>Login Form</title>
    
  </head>
  <Style>
  body
  {
  position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom:0px;
	width: auto;
	height: auto;
	background-image: url("resources/image/sup4.jpg");
	background-size: original;
	-webkit-filter: blur(0px);
	z-index: 0; 
  
}
.box{
  width: 500px;
  padding: 40px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background: transparent;
  text-align: center;
}
.box h1{
  color: white;
  text-transform: uppercase;
  font-weight: 500;
}
.box input[type = "text"],.box input[type = "password"]{
  border:0;
  background: none;
  display: block;
  margin: 20px auto;
  text-align: center;
  border: 2px solid #3498db;
  padding: 14px 10px;
  width: 200px;
  outline: none;
  color: white;
  border-radius: 24px;
  transition: 0.25s;
}
.box input[type = "text"]:focus,.box input[type = "password"]:focus{
  width: 280px;
  border-color: #2ecc71;
}
.box input[type = "submit"]{
  border:0;
  background: none;
  display: block;
  margin: 20px auto;
  text-align: center;
  border: 2px solid #2ecc71;
  padding: 14px 40px;
  outline: none;
  color: white;
  border-radius: 24px;
  transition: 0.25s;
  cursor: pointer;
}
.box input[type = "submit"]:hover{
  background: #2ecc71;
}

  </Style>
  <body>

<form class="box" action="<c:url value='/j_spring_security_check'></c:url>" method="post">
  <h1>Login</h1>
  <input type="text" name="UserName" placeholder="Username">
  <input type="password" name="Password" placeholder="Password">
  <input type="submit" name="" value="Login">

</form>


  </body>
</html>