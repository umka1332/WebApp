package ua.itea;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hellopage")
public class MyControler {
	@RequestMapping(value = "/foo", method = RequestMethod.GET, params = {"var"})
	String getString(@RequestParam("var") String var, ModelMap model) {
		model.addAttribute("msg", "taras" + var);
		return "HelloPage";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	String getString9() {
		return "HelloPage";
	}
}
