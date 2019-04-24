<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <%@ include file="HeaderForShopkart.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Shopkart</title>
</head>
<style>
body {
 background-image: url("resources/image/s9.jpg");
 }
</style>
<body>
<a href="test"><h3><b></b></h3></a>
<h1 align="center"  style="background-color:transparent">All Users List</h1>  
<table border="2" width="70%" cellpadding="2"align="center">  
<tr><th>Id</th><th>Name</th> <th>Phone No</th><th>Address</th><security:authorize access="hasRole('ROLE_USER')"><th>options</th></security:authorize></tr>  
<c:forEach var="user" items="${list}">
<tr>  
   <td ><b>${user.userId}</b></td>  
   <td><b>${user.firstName} ${user.lastName}</b></td> 
   <td><b>${user.contact}</b></td>
   <td><b>${user.userAddress}</b></td>  
  
  <security:authorize access="hasRole('ROLE_USER')">
   <td><a href="EditUser/${user.userId}" class="btn btn-warning btn-md , active" role="button"> <span class="glyphicon glyphicon-edit" > </span>edit</a></td>
   </security:authorize>
   </tr>
   </c:forEach>
  
   </table>

</body>
</html>