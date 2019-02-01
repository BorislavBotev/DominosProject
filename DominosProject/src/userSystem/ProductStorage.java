package userSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ProductStorage {
	private static Map<Product.ProductCategory,Set<Product>> menu=new HashMap<>();
	
	public void listMenu() {
		for(Entry<Product.ProductCategory,Set<Product>> entry:menu.entrySet()) {
			System.out.println(entry.getKey());
			for(Product p:entry.getValue()) {
				System.out.println(p);
			}
		}
	}
	public void addProduct(Product product) {
		if(product!=null) {
			if(!menu.containsKey(product.getProductCategory())) {
				menu.put(product.getProductCategory(), new HashSet<Product>());
			}
			menu.get(product.getProductCategory()).add(product);
		}
	}
}
