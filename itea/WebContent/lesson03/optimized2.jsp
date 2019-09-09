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
	boolean showForm = true;
	String message = "<h1 style='color:red;'>ACCESS DENIED</h1>";
	long leftTime = failTime - now();
	if (leftTime > 0) {
		showForm = false;
	} else if (login != null && password != null) {
		//When tryCounter == 3 we should check credentials, but don't show form
		if (tryCounter <= 3 && new Auth().getLogin(login, password)) {
			message = "<h1 style='color:green;'>ACCESS GRANTED</h1>";
			tryCounter = 1;
			showForm = false;
		} else if (tryCounter == 3) {
			failTime = now() + 60000;
			leftTime = 60000;
			tryCounter = 1;
			showForm = false;
			//in this case there probably be 2 output of message 
		} else {
			tryCounter++;
		}
		out.write(message);
	}
	//------------------------------------------------------------------------------
	if (leftTime > 0) {
		out.write(message);
		out.write("<br/> Time Left: " + leftTime / 1000 + "s");
	} else if (showForm) {
		out.write("<br/> Try# " + tryCounter);
%>
<center>
	<form action="optimized2.jsp">
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
