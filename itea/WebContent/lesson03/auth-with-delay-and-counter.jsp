<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Date,ua.itea.Auth"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Lesson 03 timer and delay</h1>
	<%!long failTime = 0;%>
	<%!int tries = 1;%>
	<%!final int MAX_TRIES = 3;%>
	<%
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean showForm = true;
		String message = "<h3 style=\"color:red\">Denied!</h3>";
		if (login != null && password != null) {
			if (failTime == 0) {
				if (new Auth().getLogin(login, password)) {
					message = "<h3 style=\"color:green\">Success!</h3>";
					showForm = false;
					tries = 1;
				} else {
					if (tries > MAX_TRIES) {
						failTime = new Date().getTime();
						tries = 1;
					} else {
						tries++;
					}
				}
			}
			out.print(message);
		}
		if (failTime != 0) {
			long remainigTime = failTime + 10000 - new Date().getTime();
			if (remainigTime > 0) {
				showForm = false;
				out.write("<br/>Please wait " + (remainigTime / 1000) + "s.");
			} else {
				failTime = 0;
			}
		} else {
			out.write("<br/>Try #" + tries + " out of " + MAX_TRIES);
		}
		if (showForm) {
	%>
	<form action="auth-with-delay-and-counter.jsp">
		<table>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" /></td>
			<tr />
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" /></td>
			<tr />
			<tr>
				<td></td>
				<td align="right"><input type="submit" value="Ok" /></td>
			<tr />
		</table>
	</form>
	<%
		}
	%>
</body>
</html>