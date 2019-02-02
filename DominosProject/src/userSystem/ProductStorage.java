package userSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ProductStorage {
	private static Map<Product.ProductCategory,Set<Product>> menu=new TreeMap<>();
	
	public static void listMenu() {
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

	public static Map<Product.ProductCategory, Set<Product>> getMenu() {
		return Collections.unmodifiableMap(menu);
	}
	
	
}
