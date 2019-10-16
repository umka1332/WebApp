package ua.itea.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.itea.controllers.RegisterController;
import ua.itea.models.User;

@Controller
@RequestMapping("/register")
public class RegisterServlet {
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST,
			params = {"login", "password", "rePassword", "name", "gender", "region", "comment", "amigo"})
	protected String doPost(HttpSession session, ModelMap mapping,
			String login, String password, String rePassword, String name, String gender, String region, String comment, String amigo) {
		User principal = (User)session.getAttribute("principal");
		if (name != null) { //Suppose that presence of one parameter means that we want to perform some changes
			User newPrincipal = null;
			RegisterController controller = new RegisterController();
			controller.setName(name);
			controller.setGender(gender);
			controller.setRegion(region);
			controller.setComment(comment);
			if (principal == null) { //Register user
				controller.setLogin(login);
				controller.setPassword(password);
				controller.setRePassword(rePassword);
				controller.setAmigo(amigo);
				newPrincipal = controller.createUser();
			} else { //Update user
				controller.setLogin(login);
				newPrincipal = controller.updateUser();
			}
			if (newPrincipal != null) { //There weren't any errors
				session.setAttribute("principal", newPrincipal);
			} else {
				mapping.addAttribute("errorText", controller.getErrorText());
			}
		}
		return "register";
	}

}
