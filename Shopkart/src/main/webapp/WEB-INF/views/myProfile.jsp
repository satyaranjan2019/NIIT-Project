<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  <%@ include file="HeaderForShopkart.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>User Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
                      input.hidden {
    position: absolute;
    left: -9999px;
}

#profile-image1 {
    cursor: pointer;
  
     width: 100px;
    height: 100px;
	border:2px solid #03b1ce ;}
	.tital{ font-size:16px; font-weight:500;}
	 .bot-border{ border-bottom:1px #6bd7ef solid;  margin:5px 0  5px 0}	
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<style>
body {
 background-image: url("${pageContext.request.contextPath}/resources/image/s2.jpg");
 }
</style>
<body>
<div class="container">
<c:forEach var="user" items="userDescription">
	<div class="row">
		<h2>Profile Account</h2>
        
        
       <div class="col-md-7 ">

<div class="panel panel-default">
  <div class="panel-heading">  <h4 >User Profile</h4></div>
   <div>
    <div>
   <div>
   <div  align="center">
     <h4  align="center" style="color:#00b1b1;">${userDescription.firstName} ${userDescription.lastName} </h4>
	 </div>
            <div class="clearfix"></div>
            <hr style="margin:5px 0 5px 0;">
			
			
			
			
				<div class="col-sm-5 col-xs-6 tital " >First Name:</div><div class="col-sm-7 col-xs-6 ">${userDescription.firstName}</div>
     <div class="clearfix"></div>
	 <div class="bot-border"></div>
	 
	 <div class="col-sm-5 col-xs-6 tital " >Last Name:</div><div class="col-sm-7">${userDescription.lastName} </div>
  <div class="clearfix"></div>
  <div class="bot-border"></div>
  
  <div class="col-sm-5 col-xs-6 tital " >Contact Number:</div><div class="col-sm-7">${userDescription.contact}</div>
  <div class="clearfix"></div>
  <div class="bot-border"></div>
  
  <div class="col-sm-5 col-xs-6 tital " >Address:</div><div class="col-sm-7">${userDescription.userAddress} </div>
  <div class="clearfix"></div>
  <div class="bot-border"></div>
  
  <div class="col-sm-5 col-xs-6 tital " >Password:</div><div class="col-sm-7">${userDescription.password} </div>
  <div class="clearfix"></div>
  <div class="bot-border"></div>
   </div>
          <!-- /.box -->

        </div>
        <br>
       
        <div class="form-actions">
    				<a href="${pageContext.request.contextPath}/EditUser/${userDescription.userId}" class="btn btn-large btn-primary"   role="button"><span class="glyphicon glyphicon-edit"></span>Edit</a>
    			</div>    
    </div> 
    </div>
    </div>
    </c:forEach> 
</div>  
    


    
<script type="text/javascript">

</script>
</body>
</html>
  
