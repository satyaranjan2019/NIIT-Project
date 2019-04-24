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
 background-image: url("${pageContext.request.contextPath}/resources/image/s9.jpg");
 }
</style>
<body>
<body>
<div class="container">
	<h1 align="center" style="background-color:transparent;">Edit Product</h1>
	<c:url value="/saveOrUpdateProduct" var="url"></c:url>
       <form:form method="post" action='${url }' modelAttribute="product" role="form" enctype="multipart/form-data">    
    <form:hidden path="productId" />
        <div class="form-group">
		<form:label path="productName">Product Name :</form:label>
		<form:input path="productName" class="form-control" required="true" style="width:750px" placeholder="Enter Product Name " />
		<form:errors path="productName" cssStyle="color:red"></form:errors>
        </div>
        
        <div class="form-group">
		<form:label path="productDesc"> Product Description :</form:label>
		<form:textarea path="productDesc"  class="form-control" style="width:750px" placeholder="Enter Product  Description " required="true"/>
        </div>
        
        <div class="form-group">
		<form:label path="quantity">Quantity :</form:label>
		<form:textarea path="quantity"  class="form-control" style="width:750px" placeholder="Enter Product Quantity " required="true"/>
		<form:errors path="quantity" cssStyle="color:red"></form:errors>
        </div>
        
        <div class="form-group">
		<form:label path="price">Price :</form:label>
		<form:input path="price" class="form-control" required="true" style="width:750px" placeholder="Enter Product Price "/>
		<form:errors path="price" cssStyle="color:red"></form:errors>
        </div>
        
        <div class="form-group">
        <form:label path="productCategory.categoryId">Select Category</form:label>  
        <form:select path="productCategory.categoryId">
        <c:forEach items="${categories }" var="c">
        <form:option value="${c.categoryId }">${c.categoryName }  ${c.categoryId }</form:option>
        </c:forEach>
        </form:select>
        </div>
       <div class="form-group">
		
		<form:input type="file" path="image"/>
		</div>
        
       
		<button type="submit" class="btn btn-primary" value="Edit Product"> Edit Product</button>
	</form:form>
	</div>
</body>
</html>