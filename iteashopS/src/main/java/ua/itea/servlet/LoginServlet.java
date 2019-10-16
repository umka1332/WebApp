package ua.itea.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.itea.controllers.LoginController;

@Controller
@RequestMapping("/login")
public class LoginServlet {

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet() {
		return "auth";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "login", "password" })
	protected String login(HttpSession session, String login, String password) {
		Object principal = session.getAttribute("principal");
		if (principal == null) {
			if (login != null) {
				LoginController controller = new LoginController();
				principal = controller.getPrincipal(login, password);
				session.setAttribute("principal", principal);
			}
		}
		return doGet();
	}

	@RequestMapping(method = RequestMethod.POST, params = "logout")
	protected String logout(HttpSession session, String logout) {
		if (logout != null && session.getAttribute("principal") != null) {
			session.removeAttribute("principal");
		}
		return doGet();
	}

}
