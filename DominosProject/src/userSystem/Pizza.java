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
	private Size size;
	private Dough dough;
	private Set<Ingredient> ingredients=new HashSet<Ingredient>();
	
	public Pizza(String name, double price) throws InvalidProductException, InvalidPriceException {
		super(name, price,ProductCategory.PIZZA);
		this.size=Size.LARGE;
	}

	public Pizza(String name, double price,Size size) throws InvalidProductException, InvalidPriceException {
		super(name, price,ProductCategory.PIZZA);
		this.size=size;
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
