package userSystem;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;

public class Sauce extends Product{

	public Sauce(String name, double price)
			throws InvalidProductException, InvalidPriceException {
		super(name, price, ProductCategory.SAUCE);
	}

}
