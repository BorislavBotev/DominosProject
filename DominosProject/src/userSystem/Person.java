package userSystem;

import exceptions.InvalidNameException;
import exceptions.InvalidPhoneNumberException;

public abstract class Person {
	private static final String PHONE_NUMBER_START_PREFIX = "08";
	private static final int PHONE_NUMBER_LENGTH = 10;
	
	private String name;
	private String phoneNumber;
	
	protected Person(String name, String phoneNumber) {
		try {
			this.setName(name);
			this.setPhoneNumber(phoneNumber);
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}  catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		}
	}

	private void setName(String name) throws InvalidNameException {
		if(isValidName(name)) {
			this.name = name;			
		} else throw new InvalidNameException("Invalid name!");
	}

	private void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		if(isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;			
		} else throw new InvalidPhoneNumberException("Invalid phone number!");
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber!=null && phoneNumber.trim().length()==PHONE_NUMBER_LENGTH && phoneNumber.startsWith(PHONE_NUMBER_START_PREFIX);
	}
	
	private boolean isValidName(String name) {
		return name!=null && name.trim().length()>0;
	}
	
}
