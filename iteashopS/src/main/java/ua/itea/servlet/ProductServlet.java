package ua.itea.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;

@Controller
@RequestMapping("/products")
public class ProductServlet {
	@GetMapping(params = "category")
	protected String doGet(long category, ModelMap mapping) {
		ProductDAO productDAO = DAOFactory.getDAOFactory(1).getProductDAO();
		mapping.addAttribute("productList", productDAO.getProductListByCategory(category));
		return "ProductsView";
	}
	
	@GetMapping
	protected String doGet(ModelMap mapping) {
		ProductDAO productDAO = DAOFactory.getDAOFactory(1).getProductDAO();
		mapping.addAttribute("productList", productDAO.getProductList());
		return "ProductsView";
	}

}
