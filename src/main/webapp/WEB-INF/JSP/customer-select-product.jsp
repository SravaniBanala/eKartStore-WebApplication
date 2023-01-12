<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select a Product </title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a href="${contextPath}/user/login">Customer Home</a><br/>
<h1> Select a Product </h1>
<table class="table">
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Quantity</th>
<th>Add to Cart</th>
</tr>

<c:forEach var="line" items="${requestScope.productList}">
<form:form  action="${contextPath}/cart/insert" modelAttribute="cart" method="post">
		<tr>
		<td>
		<c:out value="${line.productName}"/>
		</td>
		<td>
		<img height="250" width="220" src="${pageContext.request.contextPath}/images/${line.fileName}" class="img-rounded" />
		</td>
		
		<td>
   		<select name="quantity">
   		 <option>1</option>
   		 <option>2</option>
   		 <option>3</option>
   		 <option>4</option>
   		 <option>5</option>
 		</select>
 		</td>
 	
      <input type="hidden" name="product" id="product" value="${line.productID}"/>
      <td> 		
       <input type="submit" class="btn btn-info" id="add_to_cart" name="selection" value="Add to Cart" />
       </td>
     
      </tr>


</form:form>

</c:forEach>
</table>

<script type="text/javascript" charset="utf-8">
$(function() {
    $('#add_to_cart').click(function() {
    	alert("Item added to cart!");
    });
});
</script>


</body>



</html>