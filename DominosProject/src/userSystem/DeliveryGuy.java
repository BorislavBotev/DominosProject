package userSystem;

public class DeliveryGuy extends Person implements Runnable{
	private Restaurant restaurant;
	

	protected DeliveryGuy(String name, String phoneNumber, Restaurant restaurant) {
		super(name, phoneNumber);
	}

	@Override
	public void run() {
		
	}
	
	
}
