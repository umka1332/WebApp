<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file='/WEB-INF/includes/header.jsp'%>

<c:choose>
	<c:when test="${ principal != null }">
		<h1>Edit current user</h1>
	</c:when>
	<c:otherwise>
		<h1>Register new user</h1>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${ param['login'] == null or errorText != null or principal != null }">
	<table id="loginForm">
	<tr>
		<td>
			<form method='post'>
				<c:if test="${ principal == null }">
					<div class="field">
						<label>Login:</label>
						<div class="input">
							<input type="email" name="login"
								placeholder="Enter your email" required="required"
								value="${ param['login'] }">
						</div>
					</div>
					
					<jsp:useBean id="validation" class="ua.itea.utils.Validation"></jsp:useBean>
					<div class="field">
						<label>Password:</label>
						<div class="input">
							<input type="password" name="password" placeholder="Enter password"
								pattern="${ validation.passwordPattern }" required="required"
								value="${ param['password']}">
						</div>
					</div>
					
					<div class="field">
						<label>Retype password:</label>
						<div class="input">
							<input type="password" name="rePassword" placeholder="Enter password again"
								pattern="${ validation.passwordPattern }" required="required"
								value="${ param['rePassword'] }">
						</div>
					</div>
				</c:if>
				
				<div class="field">
					<label>Name:</label>
					<div class="input">
						<input type="text" name="name" required="required" placeholder="Enter your name"
							 value="${ param['name'] != null ? param['name'] : principal['name'] }">
					</div>
				</div>
				
				<div class="field">
					<label>Region:</label>
					<div class="input">
						<select name='region'>
							<c:set var='region' scope='page' value="${ param['region'] != null ? param['region'] : principal['region'] }"/>
							<option value='1' ${ region eq "1" ? "selected" : "" }>DNR</option>
							<option value='2' ${ region eq "2" ? "selected" : "" }>LNR</option>
							<option value='3' ${ region eq "3" ? "selected" : "" }>CREMEA</option>
						</select>
					</div>
				</div>
				
				<label>Gender:</label>
				<div class="input">
					<c:set var='gender' scope='page' value="${ param['gender'] != null ? param['gender'] : principal['gender'] }" />
					M<input type='radio' name='gender' value='Male' required="required" ${ gender eq "Male" ? "checked" : "" }>
					F<input type='radio' name='gender' value='Female' required="required" ${ gender eq "Female" ? "checked" : "" }>
				</div>
				
				<div class="field">
					<label>Comment:</label>
					<div class="input">
						<textarea name='comment' rows='5' cols='33' required="required">
							${ param['comment'] != null ? param['comment'] : principal['comment'] }
						</textarea>
					</div>
				</div>
				
				<c:if test="${ principal == null }">
					<label>
						<input type='checkbox' name='amigo' required="required" ${ param['amigo'] != null ? "checked" : "" }>
						I agree to install Amigo browser
					</label>
				</c:if>
				<div class="submit">
					<button type="submit">Enter</button>
				</div>
			</form>
		</td>
		<c:if test="${errorText != null}">
			<td>
				<ul style='color: red'>
					<c:forEach var="err" items="${errorText}">
						<li>${err}</li>
					</c:forEach>
				</ul>
			</td>
		</c:if>
		</tr>
		</table>
	</c:when>
	<c:when test='${ errorText == null }'>
		<h3>Information successfully stored!</h3>
	</c:when>
</c:choose>
<%@ include file='/WEB-INF/includes/footer.jsp'%>