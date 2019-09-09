<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Lesson 03</h1>
<%
String login=request.getParameter("login");
String password = request.getParameter("password");
boolean showForm = true;
String message="<h3 style=\"color:red\">Denied!</h3>";
if (login != null && password != null) {
	if (login.equals("admin") && password.equals("123")) {
		message="<h3 style=\"color:green\">Success!</h3>";
		showForm = false;
	}
	out.print(message);
}
if (showForm) {
%>
	<form action="auth-fine.jsp">
	    <table border='0'>
	    <tr>
	        <td>Login:</td>
	        <td><input type="text" name="login" /></td>
	    <tr/>
	    <tr>
	        <td>Password:</td>
	        <td><input type="text" name="password" /></td>
	    <tr/>
	    <tr>
	        <td></td>
	        <td align="right"><input type="submit" value="Ok" /></td>
	    <tr/>
	    </table>
	</form>
<%
}
%>
</body>
</html>