package userSystem;

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;
@XStreamAlias("pizza")
public class Pizza extends Product {
	@XStreamAlias("size")
	public enum Size {
		MEDIUM(6,5),LARGE(8,6.5),DJUMBO(12,7.5);
		private int numberOfPieces;
		private double price;
		Size(int num,double price){
			this.numberOfPieces=num;
		}
		public int getNumberOfPieces() {
			return numberOfPieces;
		}	
		public double getPrice() {
			return price;
		}
		
	}
	@XStreamAlias("dough")
	public enum Dough{
		ITALIAN,SLIGHTY,STANDART;
	}
	
	public static class HalfHalfPizza extends Pizza {
		private Pizza secondHalfPizza;
		public HalfHalfPizza(String name, double price) throws InvalidProductException, InvalidPriceException {
			super(name, price);	
			
		}
		
		public void chooseSecondHalfPizza(Pizza pizza) throws InvalidProductException {
			if(pizza!=null) {
				this.secondHalfPizza = pizza;
				this.setPrice();
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
	
	public Pizza(String name,double price) throws InvalidProductException, InvalidPriceException {
		//po dobre da mahnem cenata ot constructora
		super(name, 0,ProductCategory.PIZZA);
		this.size=Size.LARGE;
		//this.setPrice(this.getPrice()+this.size.getPrice());
	}

	public Pizza(String name,Size size) throws InvalidProductException, InvalidPriceException {
		super(name, 0,ProductCategory.PIZZA);
		this.size=size;
		//this.setPrice(this.getPrice()+this.size.getPrice());
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
		this.increasePrice(this.size.getPrice());
	}
	
	
//	public void addIngredients(Set<Ingredient> ingredients) {
//		if(ingredients!=null) {
//			for(Ingredient i:ingredients) {
//				this.addIngredient(i);
//			}
//		}
//	}
	public void addIngredient(Ingredient ingredient){
		if(ingredient!=null) {
			ingredients.add(ingredient);
			this.increasePrice(ingredient.getPrice());
		}
	}
	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return super.toString()+ " size=" + size + ", dough=" + dough + ", ingredients=" + ingredients + "]";
	}

	public void setDough(Dough dough) {
		this.dough = dough;
	}
	
	
}
