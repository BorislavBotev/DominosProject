package userSystem;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;
@XStreamAlias("dessert")
public class Dessert extends Product {

	public Dessert(String name, double price)
			throws InvalidProductException, InvalidPriceException {
		super(name, price, ProductCategory.DESSERT);
	}

}
