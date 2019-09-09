<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	pageContext.setAttribute("login", login);
	pageContext.setAttribute("password", password);
%>

<c:choose>
<c:when test="${login eq 'admin' && password eq '123' }">
ACCESS GRANTED
</c:when>
</c:choose>

<c:choose>
	<c:when test="${ login == null or not (login eq 'admin' && password eq '123' )}">
		<c:if test="${ login != null }">
			ACCESS DENIED
		</c:if>
		<form id="loginForm" action="auth2.jsp" method="get">

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