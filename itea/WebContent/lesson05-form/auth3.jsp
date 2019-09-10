<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${ param['login'] == null or not (param['login'] eq 'admin' && param['password'] eq '123' )}">
		<c:if test="${ param['login'] != null }">
			<h3>ACCESS DENIED</h3>
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