<%@ page language="java" contentType="text/html; pageEncoding=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Product Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/login">Employer Home</a><br/>

	<h2>Add a New Product</h2>


	<form:form action="${contextPath}/product/add" method="post" modelAttribute="product" enctype="multipart/form-data">
       <input type="hidden" value="" name="filename"/>
		<table>
		
			<tr>
		<td>Select Category	</td>
		
		<td>
		 <select name="category"  class="input-small">
   		 <c:forEach var="line" items="${categoryList}">
         <option><c:out value="${line.categoryName}"/></option>
   		 </c:forEach>
 		</select>
 		</td>
 		
 		</tr>
			<tr>
				<td>Product Name:</td>
				<td><form:input type="text" path="productName" size="30" required="required"/>
				<font color="red"><form:errors path="productName" /></font></td>
			</tr>

			<tr>
				<td>Product Image:</td>
				<td><input type="file" name="photo" required="required"/>
				</td>
			</tr>
			<tr>
				<td>Product Price:</td>
				<td><form:input type="number" path="productPrice" size="30" required="required"/>
				<font color="red"><form:errors path="productPrice" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Post Product" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>