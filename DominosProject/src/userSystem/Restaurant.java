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
		this.deliveryGuys = EmployeeStorage.getemployeeStorage().getDeliveryGuys();
		this.cookers = EmployeeStorage.getemployeeStorage().getCookers();
		this.receivedOrders = new LinkedBlockingQueue<Order>();
		this.preparedOrders = new LinkedBlockingQueue<Order>();
		this.cookers.forEach(cooker -> new Thread(cooker).start());
		this.deliveryGuys.forEach(deliveryGuy -> new Thread(deliveryGuy).start());
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
}
