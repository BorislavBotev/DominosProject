package userSystem;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;

public abstract class Product {
	public enum ProductCategory{
		PIZZA,DESSERT,SAUCE,DRINK	
	}
	private double price;
	private String name;
	private String photo;
	private ProductCategory productCategory;

	public double getPrice() {
		return price;
	}
	
	public Product(String name,double price,ProductCategory productCategory) throws InvalidProductException, InvalidPriceException {
		if(name==null || name.trim().length()==0) {
			throw new InvalidProductException();
		}
		this.name = name;
		this.setPrice(price);
		this.productCategory=productCategory;
		this.photo="";
	}
	public void setPrice(double price) throws InvalidPriceException {
		if(price <0) {
			throw new InvalidPriceException();
		}
		this.price = price;
	}
	public void increasePrice(double price) {
		if(price>0) {
			this.price+=price;
		}
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	@Override
	public String toString() {
		return "Category: " + this.productCategory + " - " + this.name + " - " + this.price;
	}
	
	
}
