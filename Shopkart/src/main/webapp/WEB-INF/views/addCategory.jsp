<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
   <%@ include file="HeaderForShopkart.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<style>
body {
 background-image: url("${pageContext.request.contextPath}/resources/image/s1.jpg");
 }
</style>
<body>

<div class="container">
		<h1 align="center" style="background-color:transparent;">Add Category </h1>
		<form:form method="post" action="/Shopkart/save">
		<div class="form-group">
		<form:label path="categoryName"> Category Name :</form:label>
		<form:input path="categoryName" class="form-control" required="true" style="width:750px" placeholder="Enter Category Name "/>
		</div>
		<button type="submit" class="btn btn-primary" value="Save">Save</button>
		</form:form>
		</div>
</body>
</html>