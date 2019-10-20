<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var='id' value='${product.id}' />
<tr>
	<td width="200">${product.name}</td>
	<td width="400"></td>
</tr>
<tr>
	<td><img src="<c:url value="/resources/productimages/${id}.jpg"/>" height="200"></td>
	<td>${product.description}</td>
</tr>
<tr>
	<td>Price: ${product.price} UAH</td>
	<td>
		<input type='button' value='+' name='Button${id}' id='Button${id}' onclick='buy(${id})'/>
		<span id='amount${id}'>${cart.productMap[product] == null ? 0 : cart.productMap[product] }</span>
		<input type='button' value='-' name='btn${id}' id='btn${id}' onclick='rem(${id})'/>
	</td>
</tr>