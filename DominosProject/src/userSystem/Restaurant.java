package userSystem;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import exceptions.InvalidOrderException;

public class Restaurant {
	private static Restaurant restaurant = null;
	private Set<DeliveryGuy> deliveryGuys;
	private Set<Cooker> cookers;
	private Queue<Order> receivedOrders;
	private Queue<Order> preparedOrders;
	
	
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
}
