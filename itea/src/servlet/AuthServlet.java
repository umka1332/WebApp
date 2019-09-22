package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.AuthDB;
/*
 * Hello + name for logged user + logout
 * Registration of logged user - edit 
 * можно без стилей
 */

public class AuthServlet extends HttpServlet {
	
	private final static String form = "<form method='post'>\r\n" + 
			"    <table border=\"0\">\r\n" + 
			"    <tr>\r\n" + 
			"        <td>Login:</td>\r\n" + 
			"        <td><input type=\"text\" name=\"login\" /></td>\r\n" + 
			"    <tr/>\r\n" + 
			"    <tr>\r\n" + 
			"        <td>Password:</td>\r\n" + 
			"        <td><input type=\"text\" name=\"password\" /></td>\r\n" + 
			"    <tr/>\r\n" + 
			"    <tr>\r\n" + 
			"        <td></td>\r\n" + 
			"        <td align=\"right\"><input type=\"submit\" value=\"Ok\" /></td>\r\n" + 
			"    <tr/>\r\n" + 
			"    </table>\r\n" + 
			"</form>";

	private void greatting (HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String loginAttribute = (String)session.getAttribute("Login");
		out.write("Hello " + loginAttribute +
				"<form action='' method='post'>" + 
				"<input type='hidden' value='ok' name='logout'>" + 
				"<input type='submit' value='ok'>" + 
				"</form>");
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate();
		session = req.getSession(true);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String loginAttribute = (String)session.getAttribute("Login");
		if (loginAttribute == null) {
			out.print(form);
		} else {
			greatting(req,resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String loginAttribute = (String)session.getAttribute("Login");
		if (loginAttribute == null) {
			String login = req.getParameter("login");
			String password = req.getParameter("password");
			if (new AuthDB().getLogin(login, password)) {
				out.print("<h3 style=\"color:green\">Success!<h3/>");
				session.setAttribute("Login", login);
				greatting(req,resp);
			} else {
				out.print("<h3 style=\"color:red\">Denied!<h3/>");
				out.print(form);
			}
		} else {
			greatting(req,resp);
		}
	}
}
