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

	<a href="${contextPath}/user/login">Customer Home</a><br/>

	<h2>Select a Category</h2>


	<form:form action="${contextPath}/product/productselect" method="post" modelAttribute="category">

		<table>
			<tr>
				<td>Category Title:</td>
				<td>
				<select name="category" class="input-small" onchange="document.getElementById('categoryId').value = this.value;">
				<option value="">Select an option</option>
                <c:forEach var="line" items="${categoryList}">
                <option value="${line.categoryId}"><c:out value="${line.categoryName}"/></option>
                </c:forEach>
                </select>
 		   </td>
			</tr>

			<tr>
			<input type="hidden" name="categorySelected" id="categoryId" value="">
			<td colspan="2"><input type="submit" value="Select Category" /></td>

			</tr>
		</table>

	</form:form>

</body>
</html>