package ua.itea.dao;

import java.util.List;

import ua.itea.models.Product;;

public interface ProductDAO {
	public List<Product> getProductList();
	public List<Product> getProductListByCategory(long cateory);

	public Product getProductById(long id);
}
