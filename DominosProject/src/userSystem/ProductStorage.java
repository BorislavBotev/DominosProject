package userSystem;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.XStream;

import userSystem.Product.ProductCategory;

import java.util.Set;
import java.util.TreeMap;

public class ProductStorage {
	private Map<Product.ProductCategory,Set<Product>> menu;
	private static ProductStorage productStorage = null;
	
	private ProductStorage() {
		this.menu = new TreeMap<>();
		this.downloadDataFromDataBase();
	}
	
	public static ProductStorage getProductStorage() {
		if(ProductStorage.productStorage == null) {
			productStorage = new ProductStorage();
		} 
		return productStorage;
	}
	private void downloadDataFromDataBase() {
		Products products=new Products();
		XStream xs=new XStream();
		xs.allowTypesByRegExp(new String[] { ".*" });
		xs.alias("products", Products.class);
		xs.processAnnotations(Products.class);
		products=(Products)(xs.fromXML(new File("data_base"+File.separator+"Products.xml")));
		menu.put(ProductCategory.PIZZA, new HashSet<>(products.getPizzas()));
		menu.put(ProductCategory.DRINK, new HashSet<>(products.getDrinks()));
		menu.put(ProductCategory.DESSERT, new HashSet<>(products.getDesserts()));
		menu.put(ProductCategory.SAUCE, new HashSet<>(products.getSauces()));
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
	
	private void addProduct(Product product) {
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
