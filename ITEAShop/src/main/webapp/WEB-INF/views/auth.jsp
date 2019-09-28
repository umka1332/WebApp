<!--Author: Oleksandr Naumov-->
<%@ include file='/WEB-INF/includes/header.jsp'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="counter" class="ua.itea.models.TryCounter" scope="session" />
<jsp:setProperty property="showMessage" name="counter" value='false' />
<jsp:setProperty property="showForm" name="counter" value='true' />
<jsp:setProperty property="message" name="counter" value="<h1 style='color:red;'>ACCESS DENIED</h1>" />
<jsp:useBean id="date" class="java.util.Date" />
<c:choose>
<c:when test="${counter.failTime > date.time }">
	<jsp:setProperty property="showMessage" name="counter" value='true' />
	<jsp:setProperty property="showForm" name="counter" value='false' />
</c:when>
<c:otherwise>
	<jsp:setProperty property="failTime" name="counter" value='0' />
	<c:if test="${param.login != null and param.password != null or principal != null}">
		<jsp:setProperty property="showMessage" name="counter" value='true' />
		<c:choose>
			<c:when test="${counter.tryCounter <=3 and principal != null }">
				<jsp:setProperty property="message" name="counter"
					value="<h1 style='color:green;'>ACCESS GRANTED</h1><br/>
						Hello ${principal.name}!<br/>
						<form method='post'>
							<input type='hidden' value='ok' name='logout' />
							<input type='submit' value='logout' />
						</form>" />
				<jsp:setProperty property="tryCounter" name="counter" value='1' />
				<jsp:setProperty property="showForm" name="counter" value='false' />
			</c:when>
			<c:when test="${counter.tryCounter == 3 }">
				<jsp:setProperty property="failTime" name="counter" value='${ date.time + 60000 }' />
				<jsp:setProperty property="tryCounter" name="counter" value='1' />
				<jsp:setProperty property="showForm" name="counter" value='false' />
			</c:when>
			<c:otherwise>
				<jsp:setProperty property="tryCounter" name="counter" value='${ counter.tryCounter + 1 }' />
			</c:otherwise>
		</c:choose>	
	</c:if>
</c:otherwise>
</c:choose>
<c:if test="${counter.showMessage }">
	${counter.message }
</c:if>
<c:if test="${ counter.showForm }">
	<br />Try #<c:out value="${counter.tryCounter }"></c:out>
</c:if>
<c:if test="${ counter.failTime > 0 }">
	<br /> Time Left:  00:<c:out value="${ (counter.failTime - date.time) div 1000 }"></c:out>
</c:if>
<c:if test="${counter.showForm }" >
	<form id="loginForm" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="login" value="" id="login" /></div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="password" value="" id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>
</c:if>
<%@ include file='/WEB-INF/includes/footer.jsp'%>
