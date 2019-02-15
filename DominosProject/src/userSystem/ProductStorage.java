package userSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ProductStorage {
	private Map<Product.ProductCategory,Set<Product>> menu;
	private static ProductStorage productStorage = null;
	
	public ProductStorage() {
		this.menu = new TreeMap<>();
	}
	
	public static ProductStorage getProductStorage() {
		if(ProductStorage.productStorage == null) {
			productStorage = new ProductStorage();
		} 
		return productStorage;
	}
	
	public void listMenu() {
		if(this.menu.size()>0) {
			for(Entry<Product.ProductCategory,Set<Product>> entry : this.menu.entrySet()) {
				System.out.println(entry.getKey());
				for(Product p:entry.getValue()) {
					System.out.println(p);
				}
			}
		} else {
			System.out.println("Menu is empty!");
		}
	}
	
	public void addProduct(Product product) {
		if(product!=null) {
			if(!this.menu.containsKey(product.getProductCategory())) {
				this.menu.put(product.getProductCategory(), new HashSet<Product>());
			}
			this.menu.get(product.getProductCategory()).add(product);
		}
	}

	public Map<Product.ProductCategory, Set<Product>> getMenu() {
		return Collections.unmodifiableMap(this.menu);
	}
}
