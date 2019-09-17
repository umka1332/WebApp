package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter out = resp.getWriter();
		out.print("<a href='myservlet1'>first</a> <a href='myservlet2'>second</a> <a style='font-weight:bold' href='myservlet3'>third</a> <br />Hello from servlet!");
	}
}
