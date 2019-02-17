package userSystem;

import exceptions.InvalidPersonException;

public class DeliveryGuy extends Person implements Runnable{
	private static final int DELIVERY_TIME = 3000;

	public DeliveryGuy(String name, String phoneNumber) {
		super(name, phoneNumber);
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			this.deliverOrder();
		}
	}
	
	public void deliverOrder() {
		try {
			Order order = Restaurant.getRestaurant().getPreparedOrder();
			order.addDeliveryGuy(this);
			System.out.println("Your order is on the way!");
			Thread.sleep(DELIVERY_TIME);
			System.out.println("The order was delivered!" + "\n" + order);
		} catch (InterruptedException | InvalidPersonException e) {
			return;
		}
	}
}
