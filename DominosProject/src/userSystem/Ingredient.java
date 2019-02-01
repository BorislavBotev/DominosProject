package userSystem;

import exceptions.InvalidNameException;
import exceptions.InvalidPriceException;

public class Ingredient {
	public enum IngredientsCategory {
		VEGETABLE,MEAT,SAUCE,CHEESE;
	}
	private String name;
	private IngredientsCategory category;
	private double price;
	public Ingredient(String name, IngredientsCategory category,double price) throws InvalidNameException, InvalidPriceException {
		if(name!=null && name.trim().length()!=0) {
			this.name = name;
		}
		else {
			throw new InvalidNameException("Invalid ingredient");
		}
		if(price>0) {
			this.price=price;
		}
		else {
			throw new InvalidPriceException("Invalid price");
		}
		this.category = category;
	}
	public IngredientsCategory getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
	
	
	
	
	
}
