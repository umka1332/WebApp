<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var='entry' items='${productMap}'>
	<c:set var='product' value='${entry.key}' />
	<%@ include file='/WEB-INF/includes/ShowProduct.jsp'%>
	</c:forEach>
</table>
<%@ include file='/WEB-INF/includes/scripts.jsp'%>
<%@ include file='/WEB-INF/includes/footer.jsp'%>