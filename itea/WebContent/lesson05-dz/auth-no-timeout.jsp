<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/header.jsp" %>


<jsp:useBean id="auth" class="ua.itea.AuthBean" />
<jsp:setProperty property="*" name="auth" />

<c:choose>
	<c:when test="${ param['login'] == null or not auth.accessGranted}">
		<c:if test="${ param['login'] != null }">
			<h3 style="color:red">ACCESS DENIED</h3>
		</c:if>
		<form id="loginForm" method="get">

			<div class="field">
				<label>Enter your login:</label>
				<div class="input">
					<input type="text" name="login" value="" id="login" />
				</div>
			</div>

			<div class="field">
				<a href="#" id="forgot">Forgot your password?</a> <label>Enter
					your password:</label>
				<div class="input">
					<input type="password" name="password" value="" id="pass" />
				</div>
			</div>

			<div class="submit">
				<button type="submit">Enter</button>
				<label id="remember"><input name="" type="checkbox" value="" />
					Remember me</label>
			</div>

		</form>
	</c:when>
	<c:otherwise>
		<h3>ACCESS GRANTED</h3>
	</c:otherwise>
</c:choose>
</body>
</html>