<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Home</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/logout" method="post" modelAttribute="user">
<input type="submit" value="Logout">
</form:form>

<h1>Welcome Employer, ${user.firstName} </h1>

<a href="${contextPath}/category/add" >Add a Product Category</a> <br />
<a href="${contextPath}/product/add" >Add the Product Description</a> <br />
<a href="${contextPath}/product/list" >View All Products</a> <br />


</body>
</html>