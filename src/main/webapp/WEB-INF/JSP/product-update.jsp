<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Update Product</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="javascript:history.go(-1)" class="btn btn-info">Go Back</a><br/>

	<h1>Product Update</h1>
    <div>
    <form:form action="${contextPath}/product/updatesuccess" modelAttribute="product" method="post" enctype="multipart/form-data">
	<table class="table">
	<tr>
	<th>
	Product Name
	</th>
	<th>
	Product Price
	</th>
	<th>
	Product Category
	</th>
	
	</tr>
    <tr>
    <td><input type="text" name="productName"value="${product.productName}"/></td>
    <td><input type="text" name="productPrice" value="${product.productPrice}" /></td>
    <td><select name="category" class="input-large">
   <c:forEach var="line" items="${categoryList}">
      <option <c:if test="${line.categoryId == product.category.categoryId}">selected</c:if>>
         <c:out value="${line.categoryName}"/>
      </option>
   </c:forEach>
</select>
    </td>
        <tr>
		<td>
		Select photo:</td><td> <input type="file" name="photo" value="${product.fileName}" /></td></tr>
    <tr>
    <td>
    <input type="hidden" value="" name="filename"/>
    
    <input type="submit" name="action"class="btn btn-info" value="Update" />
   </td>
   </tr>
   </table>
    <input type="hidden" name="productID" class="btn btn-info" value="${product.productID}"/>
    
    </form:form>
    </div>
</body>
</html>