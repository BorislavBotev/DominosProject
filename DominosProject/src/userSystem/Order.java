package userSystem;

import java.time.LocalTime;
import java.util.Map;

public class Order {
	private static final int ONE_PRODUCT_IN_THE_ORDER = 1;
	private Client client;
	private Map<Product,Integer> products;
	private double price;
	private DeliveryGuy deliveryGuy;
	private LocalTime time;
	
	
	public void addProduct(Product product) {
		if(product==null) {
			throw new InvalidProductException("Invalid product given");
		}
		if(!products.containsKey(product)) {
			products.put(product, ONE_PRODUCT_IN_THE_ORDER);
		}
		else {
			int count=products.get(product);
			products.put(product, ++count);	
		}
	}
	public void addDeliveryGuy(DeliveryGuy deliveryGy) {
		
	}
}
