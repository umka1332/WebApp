<%@ page import="ua.itea.Validation"%>
<%!String safe(String s) {
		return s != null ? s : "";
	}

	String check(String val, String match, String res) {
		return (val != null && val.equals(match)) ? res : "";
	}%>

<%@include file="includes/header.jsp"%>
<%
	boolean error = false;
	StringBuilder errorText = new StringBuilder();
	Validation validator = new Validation();

	String login = request.getParameter("login");
	String password = request.getParameter("password");
	String rePassword = request.getParameter("re-password");
	String name = request.getParameter("name");
	String region = request.getParameter("region");
	String gender = request.getParameter("gender");
	String comment = request.getParameter("comment");
	String amigo = request.getParameter("amigo");

	if (login != null) {
		errorText.append("<il style='color:red'>");
		if (login.isEmpty()) {
			error = true;
			errorText.append("<ul>Empty login</ul>");
		}
		if(!validator.checkEmail(login)) {
			error = false;
			errorText.append("<ul>Incorrect email used for login</ul>");
		}
		boolean passwordsPresent = true;
		if (password.isEmpty()) {
			passwordsPresent = false;
			error = true;
			errorText.append("<ul>Empty password</ul>");
		}
		if (rePassword.isEmpty()) {
			passwordsPresent = false;
			error = true;
			errorText.append("<ul>Empty re-password</ul>");
		}
		if (passwordsPresent && !password.equals(rePassword)) {
			error = true;
			errorText.append("<ul>Passwords do not match</ul>");
		}
		if (!validator.checkPassword(password)) {
			error = false;
			errorText.append("<ul>Password should contain a lowercase character, an uppercase character, a digit and at least 8 symbols</ul>");
		}
		if (name.isEmpty()) {
			error = true;
			errorText.append("<ul>Empty name</ul>");
		}
		if (region.isEmpty()) {
			error = true;
			errorText.append("<ul>region loin</ul>");
		}
		if (gender == null) {
			error = true;
			errorText.append("<ul>Empty gender</ul>");
		}
		if (comment.isEmpty()) {
			error = true;
			errorText.append("<ul>Empty comment</ul>");
		}
		if (amigo == null) { //either null, either "ok"
			error = true;
			errorText.append("<ul>Empty checkbox</ul>");
		}
		errorText.append("</il>");
		if (!error) {
%>
<p>
	<br>Login:
	<%=login%>
	<br>Password:
	<%=password%>
	<br>re-password:
	<%=rePassword%>
	<br>Name:
	<%=name%>
	<br>Region:
	<%=region%>
	<br>Gender:
	<%=gender%>
	<br>Comment:
	<%=comment%>
	<br>Amigo:
	<%=amigo%>
</p>
<%
	}
	}
	if (error || login == null) {
%>
<center>
	<table border='1'>
		<tr>
			<td>
				<form action="register.jsp">
					<table>
						<tr>
							<td>Login:</td>
							<td><input type="email" name="login"
								placeholder="Enter your email" required="required"
								value="<%=safe(login)%>"></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password"
								placeholder="Enter password"
								pattern="<%= Validation.PASWORD_PATTERN %>"
								required="required" minlength="8" value="<%=safe(password)%>">
								</td>
						</tr>
						<tr>
							<td>Retype password:</td>
							<td><input type="password" name="re-password"
								placeholder="Enter password again"
								pattern="<%= Validation.PASWORD_PATTERN %>"
								required="required" minlength="8" value='<%=safe(rePassword)%>'>
							</td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><input type="text" name="name" required="required" value="<%=safe(name)%>"></td>
						</tr>
						<tr>
							<td>Region:</td>
							<td><select name='region'>
									<option value='1' <%=check(region, "1", "selected")%>>DNR</option>
									<option value='2' <%=check(region, "2", "selected")%>>LNR</option>
									<option value='3' <%=check(region, "3", "selected")%>>CREMEA</option>
							</select></td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td>M<input type='radio' name='gender' value='Male'
								<%=check(gender, "Male", "checked")%> required="required">
								F<input type='radio' name='gender' value='Female'
								<%=check(gender, "Female", "checked")%> required="required">
							</td>
						</tr>
						<tr>
							<td>Comment:</td>
							<td><textarea name='comment' rows='5' cols='20' required="required"><%=safe(comment)%></textarea></td>
						</tr>
						<tr>
							<td>I agree to install Amigo browser</td>
							<td><input type='checkbox' name='amigo' required="required" <%=check(amigo, "ok", "checked")%>></td>
						</tr>
						<tr>
							<td></td>
							<td align="right"><input type="submit" value="send"></td>
						</tr>
					</table>
				</form>
			</td>
			<%
				if (error) {
			%>
					<td><%=errorText.toString()%></td>
			<%
				}
			%>
		</tr>
	</table>
</center>
<%
	}
%>
