package userSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("products")
public class Products {
	private List<Pizza> pizzas=new ArrayList<>();
	private List<Drink> drinks=new ArrayList<>();
	private List<Sauce> sauces=new ArrayList<>();
	private List<Dessert> desserts=new ArrayList<>();
	@Override
	public String toString() {
		return "Products [pizzas=" + pizzas + ", drinks=" + drinks + ", sauces=" + sauces + ", desserts=" + desserts
				+ "]";
	}
	public List<Pizza> getPizzas() {
		return pizzas!=null?Collections.unmodifiableList(pizzas):Collections.emptyList();
	}

	public List<Drink> getDrinks() {
		return drinks!=null?Collections.unmodifiableList(drinks):Collections.emptyList();
	}

	public List<Sauce> getSauces() {
		return sauces!=null?Collections.unmodifiableList(sauces):Collections.emptyList();
	}

	public List<Dessert> getDesserts() {
		return desserts!=null?Collections.unmodifiableList(desserts):Collections.emptyList();
	}

	
	
	
	
}
