package userSystem;

import java.util.LinkedHashSet;
import java.util.Set;

public class User extends Person implements IUser{
	
	private String eMail;
	private String password;
	private Set<Order> pastOrders;
	//discountTickets..
	
	protected User(String name, String phoneNumber, String email, String password) {
		super(name, phoneNumber);
		this.eMail = email;
		this.password = password;
		this.pastOrders = new LinkedHashSet<Order>();
	}

	@Override
	public void makeOrder() {
//		new Order(this);
	}

	public String getPassword() {
		return password;
	}
}
