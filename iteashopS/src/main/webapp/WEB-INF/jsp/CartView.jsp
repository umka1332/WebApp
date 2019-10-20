<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var='entry' items='${productMap}'>
	<c:set var='product' value='${entry.key}' />
	<%@ include file='/WEB-INF/includes/ShowProduct.jsp'%>
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