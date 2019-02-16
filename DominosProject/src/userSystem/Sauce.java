package userSystem;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;
@XStreamAlias("sauce")
public class Sauce extends Product{

	public Sauce(String name, double price)
			throws InvalidProductException, InvalidPriceException {
		super(name, price, ProductCategory.SAUCE);
	}

}
