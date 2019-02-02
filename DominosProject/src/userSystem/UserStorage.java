package userSystem;

import java.util.HashMap;
import java.util.Map;

import exceptions.EmailAlreadyExistException;
import exceptions.InvalidEMailException;
import exceptions.WrongPasswordException;

public class UserStorage {
	private static final int MIN_PASSWORD_LENGHT = 6;
	private static int usersCount = 0;
	private static Map<String, User> users=new HashMap<String,User>();
	
	public static void addNewUser(String name, String phoneNumber, String email, String password) throws EmailAlreadyExistException {
		if(UserStorage.usersCount==0) {
			UserStorage.users = new HashMap<String, User>();
		}
		
		if(!UserStorage.users.containsKey(email)) {
			if(isValidEmailAndPassword(email, password)) {
				User newUser = new User(name, phoneNumber, email, password);
				UserStorage.users.put(email, newUser);
				UserStorage.usersCount++;
				System.out.println("You registered successfully!\n");
			}
		} else {
			throw new EmailAlreadyExistException("This e-mail is already taken!");
		}	
	}
	
	public static User login(String eMail, String password) throws InvalidEMailException, WrongPasswordException {
		if(UserStorage.users.containsKey(eMail)) {
			User user = UserStorage.users.get(eMail);
			if(password.equals(user.getPassword())) {
				System.out.println("You logged in successfully!\n");
				return user;
			} else {
				throw new WrongPasswordException("Wrong Password!");
			}
		} else throw new InvalidEMailException("E-Mail was not found!");
	}

	private static boolean isValidEmailAndPassword(String email, String password) {
		return email!=null && email.trim().length()>0 && password!=null && password.trim().length()>=MIN_PASSWORD_LENGHT;
	}
	
	
}
