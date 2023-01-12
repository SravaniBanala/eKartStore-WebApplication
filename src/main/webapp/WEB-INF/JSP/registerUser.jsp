<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register User Form</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}"><input type="submit" value="Go Back"></a><br/>
	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" modelAttribute="user"
		method="post">

		<table class="table">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30" type="email"/> 
						<font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"/>
				 <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Role:</td>
				<td>
	<form:select path="role">
	<form:option value="employer">Employer</form:option>
	<form:option value="customer">Customer</form:option>
</form:select> 
	<font color="red">
	<form:errors path="role" /></font></td>
			</tr>

			
			<tr>
				<td colspan="2"><input type="submit" value="Register User" class="btn btn-successs"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>