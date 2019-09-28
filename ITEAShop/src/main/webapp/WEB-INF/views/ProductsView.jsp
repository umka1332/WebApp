
<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<c:forEach var='product' items='${productList}'>
		<tr>
			<td width="200">${ product.name }</td>
			<td width="400"></td>
		</tr>
		<tr>
			<td><img src="./productimages/${ product.id }.jpg" height="200"></td>
			<td>${product.description}</td>
		</tr>
		<tr>
			<td>Price: ${ product.price } UAH</td>
			<td>
				<form action="./cart" method="post">
					<input type='hidden' value='${ product.id }' name='productToBuy' />
					<input type='submit' value='buy' />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file='/WEB-INF/includes/footer.jsp'%>