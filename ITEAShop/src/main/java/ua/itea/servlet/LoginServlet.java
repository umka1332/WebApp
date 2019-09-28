package ua.itea.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.controllers.LoginController;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logout = request.getParameter("logout");
		HttpSession session = request.getSession(); 
		Object principal = session.getAttribute("principal");
		if (logout != null && principal != null) {
			session.invalidate();
			session = request.getSession(true);
			principal = null;
		}
		if (principal == null) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			if (login != null) {
				LoginController controller = new LoginController();
				principal = controller.getPrincipal(login, password);
				session.setAttribute("principal", principal);
				//TODO: Say Hello <name>! and propose to logout
			} else {
				//TODO: Access denied! - counter/timer/form
			}
		} else {
			//TODO: Say Hello <name>! and propose to logout
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/auth.jsp");
		rd.forward(request, response);
	}

	/**X
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
