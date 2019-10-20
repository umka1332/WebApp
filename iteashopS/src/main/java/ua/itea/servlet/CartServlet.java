package ua.itea.servlet;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping
	protected String doGet(HttpSession session, ModelMap mapping) {
		Cart cart = (Cart) session.getAttribute("cart");
		Map<Product,Integer> productMap = null;
		if (cart != null) {
			productMap = cart.getProductMap();
		}
		mapping.addAttribute("productMap", productMap);
		return "CartView";
	}

	@PostMapping(params = { "productToBuy"})
	@ResponseBody
	protected String addProduct(HttpSession session, long productToBuy) {
		Product product = getProduct(productToBuy);
		if (product != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) cart = new Cart();
			cart.addProduct(product);
			session.setAttribute("cart", cart);
			return "Ok";
		}
		return "Error";
	} 
	
	@DeleteMapping("/{productToRem}")
	protected ResponseEntity<?> doDelete(HttpSession session, @PathVariable Long productToRem) {
		Product product = getProduct(productToRem);
		HttpStatus httpStatus = HttpStatus.NOT_MODIFIED;
		String response ="Error";
		if (product != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null) {
				boolean res = cart.subProduct(product);
				session.setAttribute("cart", cart);
				if (res) {
					httpStatus = HttpStatus.OK;
					response = "Ok";
				}
			}
		}
		return new ResponseEntity<String>(response, httpStatus);
	}
	
	private Product getProduct(long id) {
		DAOFactory factory = DAOFactory.getDAOFactory(1);
		ProductDAO productDAO = factory.getProductDAO();
		Product product = productDAO.getProductById(id);
		return product;
	}
}
