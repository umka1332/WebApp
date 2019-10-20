package ua.itea.servlet;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;
import ua.itea.models.Cart;
import ua.itea.models.Product;

@Controller
@RequestMapping("/cart")
public class CartServlet {
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpSession session, ModelMap mapping) {
		Cart cart = (Cart) session.getAttribute("cart");
		List<Product> productList = Collections.emptyList();
		Map<Product,Integer> productMap = null;
		if (cart != null) {
			productList = cart.getProductList();
			productMap = cart.getProductMap();
		}
		mapping.addAttribute("productList", productList);
		mapping.addAttribute("productMap", productMap);
		mapping.addAttribute("returnLink", "./cart");
		return "CartView";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "productToBuy"})
	@ResponseBody
	protected String addProduct(HttpSession session, long productToBuy) {
		System.out.println("JQ request");
		DAOFactory factory = DAOFactory.getDAOFactory(1);
		ProductDAO productDAO = factory.getProductDAO();
		Product product = productDAO.getProductById(productToBuy);
		if (product != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) cart = new Cart();
			cart.addProduct(product);
			session.setAttribute("cart", cart);
			return "Ok";
		}
		return "Error";
	} 
	@RequestMapping(method = RequestMethod.POST, params = { "productToRem", "delete" })
	@ResponseBody
	protected String doDelete(HttpSession session, Long productToRem) {
		System.out.println("Delete Product" + productToRem);
		DAOFactory factory = DAOFactory.getDAOFactory(1);
		ProductDAO productDAO = factory.getProductDAO();
		Product product = productDAO.getProductById(productToRem);
		if (product != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null) {
				boolean res = cart.subProduct(product);
				session.setAttribute("cart", cart);
				if (res) return "Ok";
			}
		}
		return "Error";
	}
}
