
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
    <title>Sign Up </title>
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
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
            <legend><a href="http://localhost:8081/Shopkart/test"><i class="glyphicon glyphicon-globe"></i></a> Sign up!</legend>
            <form:form method="POST" action="saveUser">
             <input  path="userId" class="form-control" name="userId" placeholder="UserID" type="text" required="true" /> 
            
            <div class="row">
			
                <div class="col-xs-6 col-md-6">
                    <input path="firstName" class="form-control" name="firstName" placeholder="First Name" type="text"
                        required="true" />
                </div>
                <div class="col-xs-6 col-md-6">
                    <input  path="lastName" class="form-control" name="lastName" placeholder="Last Name" type="text" required="true" />
                </div>
            </div>
            <input  path="contact" class="form-control" name="contact" placeholder="Contact NO" type="text" pattern=".{10,}" title="Must contain ten digit" required="true"/>
                      <input   path="password" class="form-control" name="password" placeholder="Password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="true" />
                      <input  path="userAddress" class="form-control" name="userAddress" placeholder="Address" type="text" required="true" />
            
            
            
            
            <br />
            <br />
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                Sign up</button>
            </form:form>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
