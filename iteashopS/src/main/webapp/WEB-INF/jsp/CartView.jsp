<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var='entry' items='${productMap}'>
	<c:set var='id' value='${entry.key.id}' />
		<tr>
			<td width="200">${entry.key.name}</td>
			<td width="400"></td>
		</tr>
		<tr>
			<td><img src="<c:url value="/resources/productimages/${id}.jpg"/>" height="200"></td>
			<td>${entry.key.description}</td>
		</tr>
		<tr>
			<td>Price: ${entry.key.price} UAH</td>
			<td>
				<input type='button' value='+' name='Button${id}' id='Button${id}' onclick='buy(${id})'/>
				<span id='amount${id}'>${entry.value}</span>
				<input type='button' value='-' name='btn${id}' id='btn${id}' onclick='rem(${id})'/>
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