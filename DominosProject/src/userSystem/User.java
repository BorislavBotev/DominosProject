package userSystem;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.InvalidAddress;


public class User extends Person implements IUser{
	
	private String eMail;
	private String password;
	private Set<Order> pastOrders;
	private List<String> addresses;

	//discountTickets..
	
	protected User(String name, String phoneNumber, String email, String password) {
		super(name, phoneNumber);
		this.eMail = email;
		this.password = password;
		this.pastOrders = new LinkedHashSet<Order>();
		this.addresses=new ArrayList<String>();
	}

	@Override
	public void makeOrder() {
//		new Order(this);
	}
	public void addAddress(String address) throws InvalidAddress {
		if(address==null || address.trim().length()==0) {
			throw new InvalidAddress();
		}
		this.addresses.add(address);
	}

	public List<String> getAddresses() {
		return Collections.unmodifiableList(addresses);
	}

	public String getPassword() {
		return password;
	}
}
