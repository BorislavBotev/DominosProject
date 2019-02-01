package userSystem;

import java.time.LocalTime;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.InvalidProductException;

public class Order {
	private static final int INITIAL_QUANTITY = 1;
	private User client;
	private Map<Product,Integer> products;
	private double price;
	private DeliveryGuy deliveryGuy;
	private LocalTime time;
	
	
	public void addProduct(Product product) throws InvalidProductException {
		if(product==null) {
			throw new InvalidProductException("Invalid product given");
		}
		if(!products.containsKey(product)) {
			products.put(product, INITIAL_QUANTITY);
		}
		else {
			int count=products.get(product);
			products.put(product, ++count);	
		}
		price+=product.getPrice();
	}
	
	public void addDeliveryGuy(DeliveryGuy deliveryGy) {
		
	}
	
	
	public void listOrder() {
		System.out.println(this);
	}
	@Override
	public String toString() {
		StringBuilder order=new StringBuilder();
		order.append("Client "+client.getName()+"\n");
		for(Entry<Product,Integer> entry:products.entrySet()) {
			order.append(entry.getValue()+" X "+ entry.getValue()+"\n");
		}
		order.append("Your delivery guy "+deliveryGuy.getName()+"\n");
		order.append("Your order is set for "+time+"\n");
		return order.toString();
		
	}

}
