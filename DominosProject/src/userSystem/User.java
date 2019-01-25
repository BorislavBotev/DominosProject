package userSystem;

public class User extends Person implements IUser{
	
	private String eMail;
	private String password;
	//discountTickets..
	
	protected User(String name, String phoneNumber, String email, String password) {
		super(name, phoneNumber);
		this.eMail = email;
		this.password = password;
	}

	@Override
	public void makeOrder() {
		//..
	}

	public String getPassword() {
		return password;
	}
}
