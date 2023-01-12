<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/logout" method="post" modelAttribute="user">
<input type="submit" value="Logout">
</form:form>

<h1>Welcome Customer, ${user.firstName} </h1>

<a href="${contextPath}/category/select" >Select a Category</a> <br />
<a href="${contextPath}/cart/viewCart" >View Cart</a> <br />


</body>
</html>