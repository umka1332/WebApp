package ua.itea.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.itea.controllers.RegisterController;
import ua.itea.models.User;

@Controller
@RequestMapping("/register")
public class RegisterServlet {
	private static final String JSP_NAME = "register";
	
	@GetMapping
	protected String doGet() {
		return JSP_NAME;
	}

	@PostMapping(params = { "name", "gender", "region", "comment" })
	protected String doPost(HttpSession session, ModelMap mapping,
			String name, String gender, String region, String comment) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) 
			return "auth"; 
		return doPost(session, mapping, null, null, null, name, gender, region, comment, null);
	}

	@PostMapping(params = { "login", "password", "rePassword", "name", "gender", "region", "comment", "amigo" })
	protected String doPost(HttpSession session, ModelMap mapping, String login, String password, String rePassword,
			String name, String gender, String region, String comment, String amigo) {
		User principal = (User) session.getAttribute("principal");
		User newPrincipal = null;
		RegisterController controller = new RegisterController();
		controller.setName(name);
		controller.setGender(gender);
		controller.setRegion(region);
		controller.setComment(comment);
		if (principal == null) { // Register user
			controller.setLogin(login);
			controller.setPassword(password);
			controller.setRePassword(rePassword);
			controller.setAmigo(amigo);
			newPrincipal = controller.createUser();
		} else { // Update user
			controller.setLogin(principal.getLogin());
			newPrincipal = controller.updateUser();
		}
		if (newPrincipal != null) { // There weren't any errors
			session.setAttribute("principal", newPrincipal);
		} else {
			mapping.addAttribute("errorText", controller.getErrorText());
		}
		return JSP_NAME;
	}

}
