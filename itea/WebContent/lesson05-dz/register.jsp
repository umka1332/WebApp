<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>

<c:if test="${param['login'] != null}">
	<jsp:useBean id="person" class="ua.itea.Person" />
	<jsp:setProperty property="*" name="person" />
</c:if>

<c:choose>
	<c:when test="${param['login'] == null or person.error }">
	<table id="loginForm">
	<tr>
		<td>
			<form>
				<div class="field">
					<label>Login:</label>
					<div class="input">
						<input type="email" name="login"
							placeholder="Enter your email" required="required"
							value="${param['login'] }">
					</div>
				</div>
				<jsp:useBean id="validation" class="ua.itea.Validation"></jsp:useBean>
				<div class="field">
					<label>Password:</label>
					<div class="input">
						<input type="password" name="password" placeholder="Enter password"
							pattern="${validation.passwordPattern }" required="required"
							value="${param['password']}">
					</div>
				</div>
				<div class="field">
					<label>Retype password:</label>
					<div class="input">
						<input type="password" name="rePassword" placeholder="Enter password again"
							pattern="${validation.passwordPattern }" required="required"
							value="${param['rePassword'] }">
					</div>
				</div>
				<div class="field">
					<label>Name:</label>
					<div class="input">
						<input type="text" name="name" required="required" placeholder="Enter your name"
							 value="${param['name']}">
					</div>
				</div>
				<div class="field">
					<label>Region:</label>
					<div class="input">
						<select name='region'>
							<option value='1' ${ param['region'] eq "1" ? "selected" : ""}>DNR</option>
							<option value='2' ${ param['region'] eq "2" ? "selected" : ""}>LNR</option>
							<option value='3' ${ param['region'] eq "3" ? "selected" : ""}>CREMEA</option>
						</select>
					</div>
				</div>
				
				<label>Gender:</label>
				<div class="input">
					M<input type='radio' name='gender' value='Male' required="required" ${ param['gender'] eq "Male" ? "checked" : ""}>
					F<input type='radio' name='gender' value='Female' required="required" ${ param['gender'] eq "Female" ? "checked" : ""}>
				</div>
				
				<div class="field">
					<label>Comment:</label>
					<div class="input">
						<textarea name='comment' rows='5' cols='20' required="required">${param['comment']}</textarea>
					</div>
				</div>
				
				<label>
					<input type='checkbox' name='amigo' required="required" ${ param['amigo'] != null ? "checked" : ""}>
					I agree to install Amigo browser
				</label>
				
				<div class="submit">
					<button type="submit">Enter</button>
				</div>
			</form>
		</td>
		<c:if test="${person.error}">
			<td>
				<ul style='color: red'>
					<c:forEach var="err" items="${person.errorText}">
						<li>${err}</li>
					</c:forEach>
				</ul>
			</td>
		</c:if>
		</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h3>Registered</h3>
	</c:otherwise>
</c:choose>