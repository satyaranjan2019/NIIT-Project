<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
 <title>Shopkart</title>
   <script defer src="https://use.fontawesome.com/releases/v5.0.1/js/all.js"></script>
   <style>
body 
  {
   
  background-color: #e6e6e6;
}
</style>
  
  <c:set var="context" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     
      <a class="navbar-brand" href="#"><i class="fas fa-shopping-cart"></i>Shopkart</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
           <!-- each <li> </li> is one menu option -->
        <li class="active"><a href="${pageContext.request.contextPath}/test">Home</a></li>
        <!-- <li class="dropdown"> is used to create a menu option which will have sub menu -->
       <li class="dropdown">
        
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Category <span class="caret"></span></a>
         <!-- <ul class="dropdown-menu">  is used to create the sub menu items -->
          <ul class="dropdown-menu">
          
          <!-- display category names as menu option  -->
           <c:forEach items="${categoryList}" var="category">
            <li><a href="${pageContext.request.contextPath}/viewCategoryProduct/${category.categoryId}">${category.categoryName}</a></li>
             </c:forEach>
          </ul>
        </li>
        
       <security:authorize access="hasRole('ROLE_ADMIN')"><li></li></security:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        
        
        <c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li></c:if>
        <c:if test="${pageContext.request.userPrincipal.name!=null}"><li><a href="myProfile/${pageContext.request.userPrincipal.name}">Hello ${pageContext.request.userPrincipal.name}</a></li></c:if>
         <c:if test="${pageContext.request.userPrincipal.name!=null }">
				<li><a href='<c:url value="/j_spring_security_logout"></c:url>'>logout</a></li>
				</c:if>

     
      
          <c:if test="${pageContext.request.userPrincipal.name==null}"><li><a href="${pageContext.request.contextPath}/AddUser"><span class="glyphicon glyphicon-register"></span> Sign UP</a></li></c:if>
        
      </ul>
      
      <ul class="nav navbar-nav">

<security:authorize access="hasRole('ROLE_ADMIN')">
<li><a href="${pageContext.request.contextPath}/AddProduct">Add Product</a></li>
<li><a href="${pageContext.request.contextPath}/DisplayProduct">Display & Edit Product</a></li>
<li><a href="${pageContext.request.contextPath}/addCategory">Add Category</a></li>
<li><a href="${pageContext.request.contextPath}/viewcategory">Display & Edit Category </a></li>
<li><a href="${pageContext.request.contextPath}/DisplayUser">Display User-List</a></li>
</security:authorize>

<security:authorize access="hasRole('ROLE_USER')">
 
<li><a href="${pageContext.request.contextPath}/cart/viewCart">Cart</a></li>
</security:authorize>

</ul>
      
      
    </div>
  </div>
</nav>
