package userSystem;

import exceptions.InvalidOrderException;

public class Cooker extends Person implements Runnable {

	private static final int PREPARE_TIME = 3000;

	public Cooker(String name, String phoneNumber) {
		super(name, phoneNumber);
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			this.prepareOrder();
		}
	}

	public void prepareOrder() {
		try {
			Order order = Restaurant.getRestaurant().getReceiverOrder();
			System.out.println("Your order is being prepared!");
			Thread.sleep(PREPARE_TIME);
			Restaurant.getRestaurant().addPreparedOrder(order);
		} catch (InterruptedException | InvalidOrderException e) {
			return;
		}
	}
}
