package userSystem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.InvalidProductException;
import exceptions.InvalidTimeException;
import exceptions.InvalidUserException;

public class Order {
	private static final int APPROXIMATE_WAITING_TIME = 60;
	private static final int INITIAL_QUANTITY = 1;
	private User client;
	private Map<Product,Integer> products;
	private double price;
	private DeliveryGuy deliveryGuy;
	private LocalTime time;
	private String address;
	
	public Order(User client) throws InvalidUserException {
		if(client==null) {
			throw new InvalidUserException();
		}
		this.client=client;
		this.time=LocalTime.now().plusMinutes(APPROXIMATE_WAITING_TIME);
		address="";
	}
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
	public void insertDate(LocalTime time) throws InvalidTimeException {
		if(time.isBefore(this.time) || time.isAfter(LocalTime.of(00, 00))){
			throw new InvalidTimeException("Invalid time");
		}
		this.time=time;		
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
		order.append("Address delivery" + this.address);
		return order.toString();
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
