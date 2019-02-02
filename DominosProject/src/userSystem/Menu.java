package userSystem;

import java.time.LocalTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import exceptions.EmailAlreadyExistException;
import exceptions.InvalidAddress;
import exceptions.InvalidEMailException;
import exceptions.InvalidTimeException;
import exceptions.InvalidUserException;
import exceptions.WrongPasswordException;
import userSystem.Product.ProductCategory;

public class Menu{
	private static final int STARTING_INDEX = 1;
	static Scanner sc = new Scanner(System.in);

	
	public static void showMenu() {
		System.out.println("1 - Make Order \n2 - Show Menu");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:{
			try {
				showLoginMenu();
			} catch (InvalidUserException e) {
				e.printStackTrace();
			}
			break;
		}			
		case 2:{
			ProductStorage.listMenu();
			break;
		} 
		default:
			break;
		}
	}
	
	
	private static void showLoginMenu() throws InvalidUserException {
		System.out.println("1 - Login \n2 - Create New User");
		int choice = sc.nextInt();
		User user = null;
		switch (choice) {
		case 1:{
			user = showLoginVerification();
			break;
		}			
		case 2:{
			showRegistrationForm();
			user = showLoginVerification();
			break;
		} 
		default:
			break;
		}
		
		if(user != null) {
			showProductCategoriesMenu(user);
		} else throw new InvalidUserException("Invalid User!");
	}
	
	
	private static void showProductCategoriesMenu(User user) {
		ProductCategory[] categories = Product.ProductCategory.values();
		System.out.println("Please select category!\n");
		for(byte index=1; index <= categories.length; index++) {
			System.out.println(index + " - " + categories[index-1]);
		}
		int choice = sc.nextInt();
		ProductCategory category = categories[choice-1];
		
		showProductsFromCategory(category);
	}
	

	private static void showProductsFromCategory(ProductCategory category) {
		Set<Product> menu = ProductStorage.getMenu().get(category);
		
	}
	private static void choseDeliveryTime(Order order) {
		int hours,minutes;
		do {
		System.out.println("Please insert hours and minutes");
			 hours=sc.nextInt();
			 minutes=sc.nextInt();
		}
		while(hours<0 || hours>23 || minutes<0 || minutes>60);	
		try {
			order.insertDate(LocalTime.of(hours, minutes));
		} catch (InvalidTimeException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void chooseAddress(User user, Order order) throws InvalidAddress {
		System.out.println("1-Add an address"+"\n"+"2-Choose an Adress");
		int command=sc.nextInt();
		switch (command) {
		case 1:
			Menu.insertAddress(user);
		case 2:
			if(user.getAddresses().size()==0) {
				System.out.println("You got no addresses added");
				Menu.insertAddress(user);
			}
			int displayIndex=STARTING_INDEX;
			int chooseIndex;
			do {
				System.out.println("Please choose an address");
				for(String s:user.getAddresses()) {
					System.out.println("Address "+displayIndex++ +"- "+s);
				}
				chooseIndex=sc.nextInt();
			}while(chooseIndex<1 || chooseIndex>user.getAddresses().size());
			order.setAddress(user.getAddresses().get(chooseIndex));
			break;
		default:
			break;
		}
	}
	private static void insertAddress(User user) throws InvalidAddress {
		System.out.println("Please insert an address");
		user.addAddress(sc.nextLine());
	}


	private static User showLoginVerification() {
		System.out.println("-------------------------------- \nLOGIN");
		System.out.println("Please enter your e-mail!");
		String eMail = sc.next();
		System.out.println("Please enter your password!");
		String password = sc.next();
		
		try {
		 	User user = UserStorage.login(eMail, password);
		 	return user;
		} catch (InvalidEMailException | WrongPasswordException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static void showRegistrationForm() {
		System.out.println("Please enter your e-mail!");
		String eMail = sc.next();
		System.out.println("Please enter your name!");
		String name = sc.next();
		System.out.println("Please enter your phone number!");
		String phoneNumber = sc.next();
		System.out.println("Please enter your password!");
		String password = sc.next();
		
		try {
			UserStorage.addNewUser(name, phoneNumber, eMail, password);
		} catch (EmailAlreadyExistException e) {
			e.printStackTrace();
		}
	}
}
