<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,ua.itea.Auth"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Lesson 03 timer only</h1>
	<%!Date failTime = null;%>
	<%
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean showForm = true;
		String message = "<h3 style=\"color:red\">Denied!</h3>";
		if (login != null && password != null) {
			System.out.println("1");
			if (failTime == null) {
				if (login.equals("admin") && password.equals("123")) {
					System.out.println("2");
					message = "<h3 style=\"color:green\">Success!</h3>";
					showForm = false;
				} else {
					failTime = new Date();
				}
			}
			out.print(message);
		}
		if (failTime != null) {
			System.out.println("4");
			Date now = new Date();
			long remainigTime = failTime.getTime() + 60000 - now.getTime();
			if (remainigTime > 0) {
				System.out.println("5");
				showForm = false;
				out.write("Please wait " + (remainigTime / 1000) + "s.");
			} else {
				System.out.println("6");
				failTime = null;
			}
		}
		if (showForm) {
			System.out.println("7");
	%>
	<form action="auth-with-delay.jsp">
		<table border='0'>
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