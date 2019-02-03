package userSystem;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;

public class Dessert extends Product {

	public Dessert(String name, double price)
			throws InvalidProductException, InvalidPriceException {
		super(name, price, ProductCategory.DESSERT);
	}

}
