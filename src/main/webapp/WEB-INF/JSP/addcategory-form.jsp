<%@ page language="java" contentType="text/html; pageEncoding=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Category Form</title>
</head>
<body>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/login">Employer Home</a><br/>

	<h2>Add a New Category</h2>


	<form:form action="${contextPath}/category/add" method="post" modelAttribute="category">

		<table>
			<tr>
				<td>Category Title:</td>
				<td><form:input path="categoryName" size="30" required="required" /> <font color="red"><form:errors
							path="categoryName" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Create Category" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>