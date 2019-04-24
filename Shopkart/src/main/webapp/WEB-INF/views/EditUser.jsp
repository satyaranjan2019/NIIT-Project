
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <%@ include file="HeaderForShopkart.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title> EDIT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body { padding-top:30px; }
.form-control { margin-bottom: 10px; }
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<style>
body {
 background-image: url("${pageContext.request.contextPath}/resources/image/s1.jpg");
 }
</style>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
            <legend> Edit</legend>
            <form:form method="POST" action="/Shopkart/editsaveUser">
            <form:hidden  path="userId" /> 
            <form:hidden  path="cart.cartId" />
            <div class="row">
			
                <div class="col-xs-6 col-md-6">
                     <form:input path="firstName" class="form-control" required="true"/>
                </div>
                <div class="col-xs-6 col-md-6">
                    <form:input path="lastName" class="form-control" required="true"/>
                </div>
            </div>
            <form:input path="contact" class="form-control" pattern=".{10,}" title="Must contain ten digit" required="true" />
                      <form:input path="password" class="form-control" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"/>
                      <form:input path="userAddress" class="form-control"  required="true"/>
            
            
            
            
            <br />
            <br />
           <input type="submit" value="Edit Save" />
               
            </form:form>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
