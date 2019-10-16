package ua.itea.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainServlet {
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	protected String getMain() {
		return "index";
	}
}
