<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import='demo.Dota2' %>
<%-- <c:set var="login" value="Max" />
 --%>
 <c:set var="salary" value="3000" />

<%
	String login = "Max";
	pageContext.setAttribute("login", "Max");
%>

Login is ${login}
<br>
My salary = ${salary*2}
<c:if test="${salary>300}">
	<br> nadpis
</c:if>
<hr>
<c:choose>
	<c:when test="${salary<100}">
	You are mortal human
	</c:when>
	<c:when test="${salary<500}">
	You are html developer
	</c:when>
	<c:when test="${salary<1000}">
	You are java junior developer
	</c:when>
	<c:otherwise>
		You are Lisp developer
	</c:otherwise>
</c:choose>
<hr />
<c:if test="${login eq 'Max'}">
	<br> OK
</c:if>
<hr>

<% String[] arr = new String[] {"a", "b", "c", "d", "e"}; 
pageContext.setAttribute("arr", arr);
%>

<c:forEach var="i" begin="0" end="4">
I:${arr[i]}<br/>
</c:forEach>
<hr/>
<c:forEach var="i" items="${arr}">
I:${i}<br/>
</c:forEach>
<hr>
<% pageContext.setAttribute("classArr", new Dota2().getArr());%>
<c:forEach var="i" items="${classArr}">
I:${i}<br/>
</c:forEach>
<hr>
<% pageContext.setAttribute("classArr", Dota2.getArrhg());%>
<c:forEach var="i" items="${classArr}">
I:${i}<br/>
</c:forEach>
<hr>
Bean:<br/>
<jsp:useBean id="pudge" class="demo.Dota2"/>
<c:forEach var="i" items="${pudge.arr}">
I:${i}<br/>
</c:forEach>
