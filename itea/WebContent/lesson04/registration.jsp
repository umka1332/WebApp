<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if (null != request.getParameter("login")) {%>
<br/>Login <%=request.getParameter("login")%>
<br/>password <%=request.getParameter("password")%>
<br/>re-password <%=request.getParameter("re-password")%>
<br/>name <%=request.getParameter("name")%>
<br/>Region <%=request.getParameter("region")%>
<br/>Gender <%=request.getParameter("gender")%>
<br/>comment <%=request.getParameter("comment")%>
<br/>amigo <%=request.getParameter("amigo")%>
<%} else {%>
	<center>
		<form action="registration.jsp">
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="login" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Retype password:</td>
					<td><input type="password" name="re-password" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Region:</td>
					<td>
						<select name='region'>
							<option value='1'>DNR</option>
							<option value='2'>LNR</option>
							<option value='3'>CREMEA</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>M<input type='radio' name='gender' value='Male'> F<input
						type='radio' name='gender' value='Female'>
					</td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td><textarea name='comment' rows='5' cols='10'> </textarea></td>
				</tr>
				<tr>
					<td>I agree to install Amigo browser</td>
					<td><input type='checkbox' name='amigo'></td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="submit" value="send" /></td>
				</tr>
			</table>
		</form>
	</center>
	<%} %>
</body>
</html>