package ua.itea.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;
import ua.itea.models.Product;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateoryParam = request.getParameter("category");
		List<Product> productList = null;
		ProductDAO productDAO = DAOFactory.getDAOFactory(1).getProductDAO();
		String returnLink = "./products";
		if (cateoryParam == null) {
			productList  = productDAO.getProductList();
		} else {
			try {
				long category = Long.parseLong(cateoryParam);
				productList = productDAO.getProductListByCategory(category);
				returnLink+="?category="+category;
			} catch (NumberFormatException ex) {
				System.out.println("Wrong category passed in request to ProductServlet");
			}
		}
		request.setAttribute("returnLink", returnLink);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("WEB-INF/views/ProductsView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
