package ua.itea.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;
import ua.itea.models.Cart;
import ua.itea.models.Product;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		List<Product> productList = Collections.emptyList();
		if (cart != null) {
			productList = cart.getProductList();
		}
		request.setAttribute("productList", productList);
		//TODO: it also should know how to remove from cart
		request.getRequestDispatcher("WEB-INF/views//ProductsView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productToBuy = request.getParameter("productToBuy");
		if (productToBuy != null) {
			long productId = Long.parseLong(productToBuy);
			DAOFactory factory = DAOFactory.getDAOFactory(1);
			ProductDAO productDAO = factory.getProductDAO();
			Product product = productDAO.getProductById(productId);
			if (product != null) {
				HttpSession session = request.getSession();
				Cart cart = (Cart)session.getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
				}
				cart.getProductList().add(product);
				session.setAttribute("cart", cart);
			}
		}
		//TODO: maybe pass category or return link as an argument
		response.sendRedirect(request.getParameter("returnLink"));
	}
}
