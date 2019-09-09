<%@ page import="ua.itea.Validation"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/header.jsp"%>

<c:if test="${param['login'] != null}">
	<jsp:useBean id="person" class="ua.itea.Person" />
	<jsp:setProperty property="*" name="person" />
</c:if>

<c:choose>
	<c:when test="${param['login'] == null or person.error }">
		<center>
			<table border='1'>
				<tr>
					<td>
						<form>
							<table>
								<tr>
									<td>Login:</td>
									<td><input type="email" name="login"
										placeholder="Enter your email" required="required"
										value="${param['login'] }"></td>
								</tr>
								<jsp:useBean id="validation" class="ua.itea.Validation"></jsp:useBean>
								<tr>
									<td>Password:</td>
									<td><input type="password" name="password"
										placeholder="Enter password"
										pattern="${validation.passwordPattern }" required="required"
										value="${param['password']}"></td>
								</tr>
								<tr>
									<td>Retype password:</td>
									<td><input type="password" name="rePassword"
										placeholder="Enter password again"
										pattern="${validation.passwordPattern }" required="required"
										value="${param['rePassword'] }"></td>
								</tr>
								<tr>
									<td>Name:</td>
									<td><input type="text" name="name" required="required"
										value="${param['name']}"></td>
								</tr>
								<tr>
									<td>Region:</td>
									<td><select name='region'>
											<option value='1'
												${ param['region'] eq "1" ? "selected" : ""}>DNR</option>
											<option value='2'
												${ param['region'] eq "2" ? "selected" : ""}>LNR</option>
											<option value='3'
												${ param['region'] eq "3" ? "selected" : ""}>CREMEA</option>
									</select></td>
								</tr>
								<tr>
									<td>Gender:</td>
									<td>M<input type='radio' name='gender' value='Male' required="required"
										${ param['gender'] == "Male" ? "checked" : ""}>
										F<input type='radio' name='gender' value='Female' required="required"
										${ param['gender'] == "Female" ? "checked" : ""}>
									</td>
								</tr>
								<tr>
									<td>Comment:</td>
									<td><textarea name='comment' rows='5' cols='20'
											required="required">${param['comment']}</textarea></td>
								</tr>
								<tr>
									<td>I agree to install Amigo browser</td>
									<td><input type='checkbox' name='amigo'
										required="required"
										${ param['amigo'] != null ? "checked" : ""}></td>
								</tr>
								<tr>
									<td></td>
									<td align="right"><input type="submit" value="send"></td>
								</tr>
							</table>
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
		</center>
	</c:when>
	<c:otherwise>
		<h3>Registered</h3>
	</c:otherwise>
</c:choose>