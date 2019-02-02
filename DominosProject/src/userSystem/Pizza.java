package userSystem;

import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;

public class Pizza extends Product {
	public enum Size {
		MEDIUM(6),LARGE(8),DJUMBO(12);
		private int numberOfPieces;
		Size(int num){
			this.numberOfPieces=num;
		}
		public int getNumberOfPieces() {
			return numberOfPieces;
		}	
	}
	
	public enum Dough{
		ITALIAN,SLIGHTY,STANDART
	}
	
	public static class HalfHalfPizza extends Pizza {
		private Pizza secondHalfPizza;
		public HalfHalfPizza(String name, double price) throws InvalidProductException, InvalidPriceException {
			super(name, price);	
			this.setPrice();
		}
		
		public void chooseSecondHalfPizza(Pizza pizza) throws InvalidProductException {
			if(pizza!=null) {
				this.secondHalfPizza = pizza;
			} else {
				throw new InvalidProductException("Invalid Product");
			}
		}
		
		@Override
		public void chooseADough(Dough dough) {
			super.dough=dough;
			this.secondHalfPizza.dough = dough;
		}

		@Override
		public void chooseSize(Size size) {
			super.size = size;
			this.secondHalfPizza.size=size;
		}
		
		private void setPrice() {
			try {
				super.setPrice((this.getPrice()/2) + (this.secondHalfPizza.getPrice()/2));
			} catch (InvalidPriceException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Size size;
	private Dough dough;
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	public Pizza(String name, double price) throws InvalidProductException, InvalidPriceException {
		super(name, price,ProductCategory.PIZZA);
		this.size=Size.LARGE;
	}

	public Pizza(String name, double price,Size size) throws InvalidProductException, InvalidPriceException {
		super(name, price,ProductCategory.PIZZA);
		this.size=size;
	}
	
	public Pizza(String name, double price, Set<Ingredient> ingredients) throws InvalidProductException, InvalidPriceException {
		super(name, price,ProductCategory.PIZZA);
		if(ingredients!=null) {
			this.ingredients = ingredients;
		}
	}
	
	public void chooseADough(Dough dough) {
		this.dough=dough;
	}
	
	public void chooseSize(Size size) {
		this.size=size;
	}
	
	
	public void addIngredients(Set<Ingredient> ingredients) {
		if(ingredients!=null) {
			for(Ingredient i:ingredients) {
				this.addIngredient(i);
			}
		}
	}
	public void addIngredient(Ingredient ingredient){
		if(ingredient!=null) {
			ingredients.add(ingredient);
			this.increasePrice(ingredient.getPrice());
		}
	}
	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}
	
}
