package ua.itea.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.controllers.RegisterController;
import ua.itea.models.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		User principal = (User)session.getAttribute("principal");
		if (principal != null && request.getParameter("logout") != null) {
			session.invalidate();
			session = request.getSession(true);
			principal = null;
		}
		String name = request.getParameter("name");
		if (name != null) { //Suppose that presence of one parameter means that we want to perform some changes
			User newPrincipal = null;
			RegisterController controller = new RegisterController();
			controller.setName(name);
			controller.setGender(request.getParameter("gender"));
			controller.setRegion(request.getParameter("region"));
			controller.setComment(request.getParameter("comment"));
			if (principal == null) { //Register user
				controller.setLogin(request.getParameter("login"));
				controller.setPassword(request.getParameter("password"));
				controller.setRePassword(request.getParameter("rePassword"));
				controller.setAmigo(request.getParameter("amigo"));
				newPrincipal = controller.createUser();
			} else { //Update user
				controller.setLogin(principal.getLogin());
				newPrincipal = controller.updateUser();
			}
			if (newPrincipal != null) { //There weren't any errors
				session.setAttribute("principal", newPrincipal);
			} else {
				request.setAttribute("errorText", controller.getErrorText());
			}
		}
		request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
