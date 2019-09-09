<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Lesson 03 second task</h1>
<%!
int cnt = 0;
final int maxTries=3;
Date time = null;
%>
<%
String login=request.getParameter("login");
String password = request.getParameter("password");
boolean showForm = true;
String message="<h3 style=\"color:red\">Denied!</h3>";
if (login != null && password != null) {
	if (login.equals("admin") && password.equals("123")) {
		message="<h3 style=\"color:green\">Success!</h3>";
		cnt = 3;
		showForm = false;
	} else {
		cnt++;
	}
	if (cnt>3) {
		showForm=false;
	}
	out.print(message);
	
}
if (showForm) {
	/*
	Date now = new Date();
	cnt++;
	if (cnt > maxTries) {
		if (time == null) {
		 	time = new Date(now.getTime() + 60000);
		 	showForm = false;
		}
		if (time.getTime() - now.getTime() < 0) {
			cnt = 0;
			time = null;
			showForm = true;
		} else {
			out.write("Tries excieded! Please wait " + (time.getTime() - now.getTime() / 1000) + "s");
		}	
	}
	out.write("Tries to login: " + (maxTries - cnt + 1));
	*/
%>
	<form action="auth2.jsp">
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