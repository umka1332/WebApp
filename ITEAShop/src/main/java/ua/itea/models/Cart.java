package ua.itea.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> productList = new ArrayList<Product>();

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public int getProductCount() {
		return productList.size();
	}
}
