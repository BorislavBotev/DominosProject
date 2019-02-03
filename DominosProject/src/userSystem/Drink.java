package userSystem;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;

public class Drink extends Product {
	public enum Liters{
		HALF_A_LITER,LITER_AND_25,TWO_LITERS;
	}
	private Liters liters;

	public Drink(String name, double price,Liters liters)
			throws InvalidProductException, InvalidPriceException {
		super(name, price, ProductCategory.DRINK);
		this.liters=liters;
	}

}
