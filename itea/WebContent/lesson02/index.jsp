<%! private int cnt = 0; %>

<%@ page import="java.util.*,ua.dota2.Hero" %>

<h1>Lesson 02</h1>
<%
String login=request.getParameter("login");
String password = request.getParameter("password");
/*
String res="";
if (login != null && password != null) {
out.print(login);
    if (login.equals("admin") && password.equals("123")) {
        res = "<h3 style=\"color:green\">Success!<h3/>";
    } else {
        res = "<h3 style=\"color:red\">Denied!<h3/>";
    }
}
out.println(res);
*/
if (login != null && password != null) {
    if (login.equals("admin") && password.equals("123")) {
    %>
        <h3 style="color:green">Success!</h3>
    <%
    } else {
    %>
        "<h3 style="color:red">Denied!</h3>"
    <%
    }
}
%>
<br/>
<%
int i = 9;
out.write(String.valueOf(i));
String[] strings = new String[] {"Tesla", "Ford", "AUDI"};
for (String s :strings) { out.write(s + "<br/>");}
%>

<h2>Next </h2>
<p>
<%
out.write("Next p");  
%>
</p>

<%
    Date date = new Date();
    out.write(date.toString());
    //import could be anywhere but bestpractice - at the top of file
    out.write("<br/>" + new Hero().getter() + "<br/>");
%>

<a href="index.jsp">My page</a>

<%
out.write("<br/>" + cnt++ + "<br/>");
%>