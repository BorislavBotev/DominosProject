package userSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import exceptions.InvalidOrderException;
import exceptions.InvalidPersonException;

public class Restaurant {
	private static Restaurant restaurant = null;
	private Set<DeliveryGuy> deliveryGuys;
	private Set<Cooker> cookers;
	private LinkedBlockingQueue<Order> receivedOrders;
	private LinkedBlockingQueue<Order> preparedOrders;
	
	
	private Restaurant() {
		this.deliveryGuys = new HashSet<DeliveryGuy>();
		this.cookers = new HashSet<Cooker>();
		this.receivedOrders = new LinkedBlockingQueue<Order>();
		this.preparedOrders = new LinkedBlockingQueue<Order>();
	}
	
	public static Restaurant getRestaurant() {
		if(restaurant == null) {
			restaurant = new Restaurant();
		}
		return restaurant;
	}
	
	public void receiveOrder(Order order) throws InvalidOrderException {
		if(order != null) {
			this.receivedOrders.add(order);
			System.out.println("Your order was received!");
		} else throw new InvalidOrderException("Invalid order!");
	}
	
	public Order getReceiverOrder() throws InterruptedException {
		return this.receivedOrders.take();
	}
	
	public void addPreparedOrder(Order order) throws InvalidOrderException {
		if(order != null) {
			this.preparedOrders.add(order);
		} else throw new InvalidOrderException("Invalid order!");
	}
	
	public Order getPreparedOrder() throws InterruptedException {
		return this.preparedOrders.take();
	}
	
	public void addDeliveryGuy(DeliveryGuy deliveryGuy) throws InvalidPersonException {
		if(deliveryGuy != null) {
			this.deliveryGuys.add(deliveryGuy);
		} else throw new InvalidPersonException("Invalid Delivery Guy!");
	}
	
	public void addCooker(Cooker cooker) throws InvalidPersonException {
		if(cooker != null) {
			this.cookers.add(cooker);
		} else throw new InvalidPersonException("Invalid Cooker!");
	}
}
