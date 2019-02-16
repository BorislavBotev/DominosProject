package userSystem;

import java.util.LinkedHashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.InvalidAddress;
import exceptions.InvalidOrderException;

@XStreamAlias("user")
public class User extends Person implements IUser{
	
	private String eMail;
	private String password;
	//private Set<Order> pastOrders;
	@XStreamAlias("addresses")
	private List<String> addresses;
	
	protected User(String name, String phoneNumber, String email, String password) {
		super(name, phoneNumber);
		this.eMail = email;
		this.password = password;
	//	this.pastOrders = new LinkedHashSet<Order>();
		this.addresses=new ArrayList<String>();
	}

	@Override
	public void makeOrder(Order order) {
		//this.pastOrders.add(order);
		try {
			Restaurant.getRestaurant().receiveOrder(order);
		} catch (InvalidOrderException e) {
			e.getMessage();
		}
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

	@Override
	public String toString() {
		return "User [eMail=" + eMail + ", password=" + password + ", addresses=" + addresses + "]";
	}


	
}
