package userSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.thoughtworks.xstream.XStream;

import exceptions.EmailAlreadyExistException;
import exceptions.InvalidAddress;
import exceptions.InvalidDataExcecption;
import exceptions.InvalidEMailException;
import exceptions.WrongPasswordException;

@XmlRootElement(name = "users")
public class UserStorage {
	private static final String PHONE_NUMBER_START_PREFIX = "08";
	private static final int PHONE_NUMBER_LENGTH = 10;
	private static final int MIN_PASSWORD_LENGHT = 6;
	private static int usersCount = 0;
	private Map<String, User> users;
	private static UserStorage userStorage = null;
	
	private UserStorage() {
		this.users = new HashMap<String,User>();
	}
	
	public static UserStorage getUserStorage() {
		if(UserStorage.userStorage == null) {
			userStorage = new UserStorage();
		} 
		return userStorage;
	}
	
	public void addNewUser(String name, String phoneNumber, String email, String password) throws EmailAlreadyExistException, InvalidDataExcecption {
		if(UserStorage.usersCount==0) {
			this.users = new HashMap<String, User>();
		}
		
		if(!this.users.containsKey(email)) {
			if(isValidEmailAndPasswordAndPhone(email, password, phoneNumber)) {
				User newUser = new User(name, phoneNumber, email, password);
				this.users.put(email, newUser);
				UserStorage.usersCount++;
				System.out.println("You registered successfully!\n");
				//Users.addUser(newUser);
				
			} else {
				throw new InvalidDataExcecption("Invalid phone number or password field!");
			}
		} else {
			throw new EmailAlreadyExistException("This e-mail is already taken!");
		}	
	}
	
	private void downloadDataFromFile(){
		XStream xs=new XStream();
		xs.allowTypesByRegExp(new String[] { ".*" });
		xs.alias("address", String.class);
		xs.alias("Order", Order.class);
		xs.processAnnotations( Users.class);
		File f=new File("data_base"+File.separator+"Users.xml");
		byte b=0;
		try {
			b=(byte)(new FileInputStream(f).read());
		} catch (FileNotFoundException e) {
			return;
		} catch (IOException e) {
			return;
		}
		System.out.println(b);
		if(b==-1) {
			return;
		}
		Users is=(Users)(xs.fromXML(new File("data_base"+File.separator+"Users.xml")));
		for(User u:is.getUsers()) {
			users.put(u.getName(), u);
		}
	}
	
//	private void downloadDataFromFile2() throws JAXBException{
//		JAXBContext jc = JAXBContext.newInstance(UserStorage.class);
//		 
//		  Unmarshaller unmarshaller = jc.createUnmarshaller();
//		  UserStorage unMarshCustomer = (UserStorage) unmarshaller.unmarshal(new File("data_base" + File.separator + "users2.xml"));
//	}
	
	public User login(String eMail, String password) throws InvalidEMailException, WrongPasswordException {
		if(this.users.containsKey(eMail)) {
			User user = this.users.get(eMail);
			if(password.equals(user.getPassword())) {
				System.out.println("You logged in successfully!\n");
				return user;
			} else {
				throw new WrongPasswordException("Wrong Password!");
			}
		} else throw new InvalidEMailException("E-Mail was not found!");
	}

	private boolean isValidEmailAndPasswordAndPhone(String email, String password, String phoneNumber) {
		return email!=null && email.trim().length()>0 
						&& password!=null 
						&& password.trim().length()>=MIN_PASSWORD_LENGHT 
						&& phoneNumber.trim().length() == PHONE_NUMBER_LENGTH
						&& phoneNumber.trim().startsWith(PHONE_NUMBER_START_PREFIX);
	}

	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, User> getUsers() {
		return users;
	}
}
