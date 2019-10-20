package ua.itea.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private Map<Product,Integer> productMap = new HashMap<Product, Integer>();
	
	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public List<Product> getProductList() {
		List<Product> res = new ArrayList<Product>();
		for(Map.Entry<Product, Integer> productPair : productMap.entrySet()) {
			Product product = productPair.getKey();
			for(int i = 0; i < productPair.getValue(); i++) {
				res.add(product);
			}
		}
		return res;
	}

	public void setProductList(List<Product> productList) {
		productMap.clear();
		productList.stream().forEach(p-> addProduct(p));
	}
	
	public int getProductCount() {
		Integer count = productMap.values().stream().mapToInt(Integer::intValue).sum();
		return count;
	}
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}
	
	public void addProduct(Product product, int amount) {
		productMap.put(product, productMap.getOrDefault(product, 0) + amount);
	}
	
	public boolean subProduct(Product product) {
		if (productMap.containsKey(product)) {
			int newCount = productMap.get(product)-1;
			if (newCount <= 0) {
				productMap.remove(product);
			} else {
				productMap.put(product, newCount);
			}
			return true;
		}
		return false;
	}
}
