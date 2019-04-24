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
<title>Insert title here</title>
</head>
<style>
body {
 background-image: url("${pageContext.request.contextPath}/resources/image/s9.jpg");
 }
</style>
<body>

<h1 align="center" style="background-color:transparent;" >Product Details</h1> 
   
   <div style="text-align:center">
  <img src="${pageContext.request.contextPath}/${productDescription.productImage}"style="width: 400px; height: 300px;">
  <h1>${productDescription.productName}</h1>
 <b><font size="3">Product Details:</font></b>  ${productDescription.productDesc}<br><b>Rs:</b>  ${productDescription.price}<br><b><font size="3">Product Quantity:</font></b>  ${productDescription.quantity}<br><b><font size="3">Product Category:</font></b>  ${productDescription.productCategory.categoryName}</font>
    <security:authorize access="hasRole('ROLE_ADMIN')"> 
    <P><a href="${pageContext.request.contextPath}/EditProduct/${productDescription.productId}"><button type="button" onclick="">EDIT </button> </a></P> 
   <P><a href="${pageContext.request.contextPath}/deleteProduct/${productDescription.productId}"><button type="button" onclick="">DELETE </button></a></P>



</security:authorize>
 <security:authorize access="hasRole('ROLE_USER')">
<P><a href="${pageContext.request.contextPath}/cart/addtocart/${productDescription.productId}?requestedQuantity=1"><button type="button" class="btn btn-primary">Add To Cart</button></a></P> 
  </security:authorize> 
</div>

</body>
</html>