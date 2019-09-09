<!--Author: Oleksandr Naumov-->
<%@include file="includes/header.jsp" %>
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
			if (tryCounter <= 3 && new Auth().getLogin(login, password)) {
				message = "<h1 style='color:green;'>ACCESS GRANTED</h1>";
				tryCounter = 1;
				showForm = false;
			} else {
				if (tryCounter == 3) {
					failTime = now() + 60000;
					tryCounter = 1;
					showForm = false;
				} else {
					tryCounter++;
				}
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
<form id="loginForm" action="auth.jsp" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="login" value="" id="login" /></div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="password" value="" id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>

</body>
</html>
<%
	}
%>
