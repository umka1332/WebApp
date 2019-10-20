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
