<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var='product' items='${productList}'>
		<tr>
			<td width="200">${product.name}</td>
			<td width="400"></td>
		</tr>
		<tr>
			<td><img src="<c:url value="/resources/productimages/${product.id}.jpg"/>" height="200"></td>
			<td>${product.description}</td>
		</tr>
		<tr>
			<td>Price: ${product.price} UAH</td>
			<td>
				<form action="./cart" method="post">
					<input type='hidden' value='${product.id}' name='productToBuy' />
					<input type='hidden' value='<c:url value="${returnLink}"/>' name='returnLink' />
					<input type='submit' value='Add to cart' />
				</form>
				<input type='button' value='JQ buy' name='Button${product.id}' id='Button${product.id}' onclick='buy(${product.id})'/>
				<form action="./cart" method="post">
					<input type='hidden' value='delete' name='delete'/>
					<input type='hidden' value='${product.id}' name='productToBuy' />
					<input type='hidden' value='<c:url value="${returnLink}"/>' name='returnLink' />
					<input type='submit' value='Remove from cart' />
				</form>
				<input type='button' value='JQ rem' name='btn${product.id}' id='btn${product.id}' onclick='rem(${product.id})'/>
			</td>
		</tr>
	</c:forEach>
</table>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script>
function buy(id) {
	$.post('./cart', { productToBuy: id }, function(data, status){ alert("Data: " + data + "\nStatus: " + status); });
}
function rem(id) {
	$.post('./cart', { productToBuy: id, delete: 'delete' }, function(data, status){ alert("Data: " + data + "\nStatus: " + status); });
}

</script>

<%@ include file='/WEB-INF/includes/footer.jsp'%>