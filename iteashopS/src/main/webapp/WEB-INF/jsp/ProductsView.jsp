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
				<input type='button' value='+' name='Button${product.id}' id='Button${product.id}' onclick='buy(${product.id})'/>
				<span id='amount${product.id}'>${cart.productMap[product] == null ? 0 : cart.productMap[product] }</span>
				<input type='button' value='-' name='btn${product.id}' id='btn${product.id}' onclick='rem(${product.id})'/>
			</td>
		</tr>
	</c:forEach>
</table>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script>
function buy(id) {
	$.post('./cart',
			{ productToBuy: id },
			function(data, status){
				//alert("status: "+status+"\ndata: "+data);
				if(data == "Ok") {
					document.getElementById("totalAmount").innerHTML++;
					document.getElementById("amount"+id).innerHTML++;
				}
			});
}
function rem(id) {
	$.ajax({ url: './cart/'+id,
			 type: 'DELETE',
			 success: function(result){
				 //alert(result);
				 if(result=="Ok") {
					 document.getElementById("totalAmount").innerHTML--;
					 document.getElementById("amount"+id).innerHTML--;
					 }
				 }
			});	
}
</script>

<%@ include file='/WEB-INF/includes/footer.jsp'%>