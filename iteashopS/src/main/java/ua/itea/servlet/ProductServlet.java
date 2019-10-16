package ua.itea.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;

@Controller
@RequestMapping("/products")
public class ProductServlet {
	@RequestMapping(method = RequestMethod.GET, params = "category")
	protected String doGet(long category, ModelMap mapping) {
		ProductDAO productDAO = DAOFactory.getDAOFactory(1).getProductDAO();
		mapping.addAttribute("returnLink", "./products?category=" + category);
		mapping.addAttribute("productList", productDAO.getProductListByCategory(category));
		return "ProductsView";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap mapping) {
		ProductDAO productDAO = DAOFactory.getDAOFactory(1).getProductDAO();
		mapping.addAttribute("returnLink", "./products");
		mapping.addAttribute("productList", productDAO.getProductList());
		return "ProductsView";
	}

}
