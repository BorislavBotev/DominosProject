package userSystem;

public class Cooker extends Person implements Runnable {
	private Restaurant restaurant;


	protected Cooker(String name, String phoneNumber, Restaurant restaurant) {
		super(name, phoneNumber);
		
	}

	@Override
	public void run() {
		
	}

}
