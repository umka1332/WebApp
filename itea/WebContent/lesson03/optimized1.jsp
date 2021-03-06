<!--Author: Oleksandr Naumov-->
<%@page import="java.util.Date"%>
<%@page import="ua.itea.Auth"%>
<%!int tryCounter = 1;%>
<%!long failTime = 0;%>
<%!long now() {
		return new Date().getTime();
	}%>
<%
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	//By default we enter page for the first time with no parameters in request
	boolean showMessage = false;
	boolean showForm = true;
	String message = "<h1 style='color:red;'>ACCESS DENIED</h1>";
	if (failTime > now()) {
		showMessage = true;
		showForm = false;
	} else {
		failTime = 0;
		if (login != null && password != null) {
			showMessage = true;
			//When tryCounter == 3 we should check credentials, but don't show form
			boolean accessGranted = tryCounter <= 3 && new Auth().getLogin(login, password);
			boolean loginFailed = tryCounter++ == 3 && !accessGranted;
			if (accessGranted)
				message = "<h1 style='color:green;'>ACCESS GRANTED</h1>";
			if (loginFailed)
				failTime = now() + 60000;
			if (loginFailed || accessGranted) {
				tryCounter = 1;
				showForm = false;
			}
		}
	}
	//------------------------------------------------------------------------------
	if (showMessage)
		out.write(message);
	if (showForm)
		out.write("<br/> Try# " + tryCounter);
	if (failTime > 0)
		out.write("<br/> Time Left:  00:" + (failTime - now()) / 1000);
	if (showForm) {
%>
<center>
	<form action="optimized1.jsp">
		<table border='0'>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="submit" value="send" /></td>
			</tr>
		</table>
	</form>
</center>

<%
	}
%>
