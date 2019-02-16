package userSystem;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;
@XStreamAlias("drink")
public class Drink extends Product {
	@XStreamAlias("liter")
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
