<%@ page language="java" contentType="text/html; pageEncoding=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Added</title>
    </head>
    <body>
    	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	    <a href="${contextPath}/user/login">Employer Home</a><br/>
    
        <h2>Product Created Successfully: ${product.productName}</h2>
        
    </body>
</html>