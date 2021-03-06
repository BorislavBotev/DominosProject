package userSystem;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import exceptions.EmailAlreadyExistException;
import exceptions.InvalidChoiceException;
import exceptions.InvalidDataExcecption;
import exceptions.InvalidEMailException;
import exceptions.InvalidPriceException;
import exceptions.InvalidProductException;
import exceptions.InvalidAddress;
import exceptions.InvalidEMailException;
import exceptions.InvalidTimeException;
import exceptions.InvalidUserException;
import exceptions.WrongPasswordException;
import userSystem.Pizza.Dough;
import userSystem.Pizza.Size;
import userSystem.Pizza.HalfHalfPizza;
import userSystem.Product.ProductCategory;

public class Menu{
	private static final int MAX_MINUTES = 60;
	private static final int MIN_MINUTES = 0;
	private static final int MAX_HOURS = 23;
	private static final int MIN_HOURS = 0;
	private static final int STARTING_INDEX = 0;
	private static Scanner sc = new Scanner(System.in);
	private static Menu menu = null;
	
	public static Menu getMenu() {
		if(Menu.menu == null) {
			menu = new Menu();
		} 
		return menu;
	}
	
	public void showMenu() {
		System.out.println("1 - Make Order \n2 - Show Menu");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:{
			try {
				this.showLoginMenu();
			} catch (InvalidUserException e) {
				e.printStackTrace();
			}
			break;
		}			
		case 2:{
			ProductStorage.getProductStorage().listMenu();
			break;
		} 
		default:
			break;
		}
	}
	
	
	private void showLoginMenu() throws InvalidUserException  {
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
		if(user==null) {
			return;
		}
		this.orderMenu(user);
	}
	
	private void orderMenu(User user) throws InvalidUserException {
		if(user != null) {
			try {
				Order order = new Order(user);
				while(!order.isFinalized()) {
					System.out.println("1 - Add Product\n2 - Remove Product\n"
							+ "3 - See temporary order\n4 - Finalize Order");
					byte choice = sc.nextByte();
					
					switch (choice) {
					case 1:{
						showProductCategoriesMenu(order);
						break;
					}			
					case 2:{
						order.removeProduct();
						break;
					}
					case 3:{
						System.out.println(order.seeTemporaryOrder());
						break;
					}
					case 4:{
						if(!order.isEmpty()) {
							chooseAddress(user, order);
							chooseDeliveryTime(order);
							order.calculatePrice();
							order.setFinalized(true);
							user.makeOrder(order);
						}
						break;
					}
					default:
						break;
					}
				}
				
			} catch (InvalidChoiceException e) {
				e.printStackTrace();
			} catch (InvalidAddress e) {
				e.printStackTrace();
			}
		} else throw new InvalidUserException("Invalid User!");
	}
	
	
	private void showProductCategoriesMenu(Order order) throws InvalidChoiceException {
		ProductCategory[] categories = Product.ProductCategory.values();
		int choice=0;
		do {
		System.out.println("Please select category!\n");
		for(byte index=0; index < categories.length; index++) {
			System.out.println((index+1) + " - " + categories[index]);
		}
		 choice = sc.nextInt() - 1;
		}
		while(choice<0 || choice>=categories.length) ;
		ProductCategory category = categories[choice];			
		showProductsFromCategory(order, category);
			
	}
	

	private void showProductsFromCategory(Order order, ProductCategory category) {
		Set<Product> menu = ProductStorage.getProductStorage().getMenu().get(category);
		
		if(category.equals(Product.ProductCategory.PIZZA)) {
			showPizzaOrderMenu(order);
		} else {			
			List<Product> products = new ArrayList<Product>(menu);
			for(int index=0; index < products.size(); index++) {
				System.out.println((index+1) + " - " + products.get(index));
			}
			int choice = sc.nextShort() - 1 ;
			if(isValidChoice(products, (short)choice)) {
				Product choosenProduct = products.get(choice);
				try {
					order.addProduct(choosenProduct);
				} catch (InvalidProductException e) {
					e.printStackTrace();
				}
			}
		}
	}



	private void showPizzaOrderMenu(Order order) {
		System.out.println("1 - Choose from menu \n2 - Half/Half \n3 - Make your own");
		byte choice = (byte) sc.nextInt();
		Pizza pizza = null;
		try {
			switch (choice) {
			case 1:{
				pizza = orderPizzaFromMenu();
				break;
			}
			case 2:{
				try {
					pizza = orderHalfHalfPizza();
				} catch (InvalidPriceException e) {
					e.printStackTrace();
				}
				break;
			}
			case 3:{
				pizza = createYourOwnPizza();
				break;
			}	
			default:
				throw new InvalidChoiceException("Invalid command!");
			}	
		} catch (InvalidChoiceException e) {
			System.out.println(e.getMessage());
		}
		
		
		if(pizza!=null) {
			try {
				pizza = setUpAPizza(pizza);
				order.addProduct(pizza);
			} catch (InvalidChoiceException | InvalidProductException e) {
				e.printStackTrace();
			}
		}
	}
	

	private Pizza orderPizzaFromMenu() throws InvalidChoiceException {
		Set<Product> pMenu= ProductStorage.getProductStorage().getMenu().get(ProductCategory.PIZZA);
		List<Product> pizzaMenu = new ArrayList<Product>(pMenu);
		for(short index=0; index<pizzaMenu.size(); index++) {
			System.out.println((index+1) + " - " + pizzaMenu.get(index));
		}
		
		short choice=0;
		do {
			System.out.println("Choose pizza");
			choice = (short) (sc.nextShort() - 1);
		}while(!isValidChoice(pizzaMenu, choice));
		
		Pizza pizza = (Pizza) pizzaMenu.get(choice);
		return pizza;
	}

	
	private Pizza orderHalfHalfPizza() throws InvalidChoiceException, InvalidPriceException {
		try {
			Pizza p = orderPizzaFromMenu();
			System.out.println("Choose first half!");
			Pizza.HalfHalfPizza pizza = new Pizza.HalfHalfPizza(p.getName(), p.getPrice());
			System.out.println("Choose second half!");
			pizza.chooseSecondHalfPizza(orderPizzaFromMenu());
			return pizza;
		} catch (InvalidChoiceException | InvalidProductException | InvalidPriceException e) {
			e.printStackTrace();
		}
		throw new InvalidChoiceException("Invalid command!");
	}
	
	
	private Pizza setUpAPizza(Pizza pizza) throws InvalidChoiceException {
		int index=0;
		do {
			System.out.println("Please choose the size of the pizza");
			index=1;
			for(Pizza.Size s:Pizza.Size.values()) {
				System.out.println(index++ +"- "+s);
			}
			index = sc.nextInt();
			pizza.chooseSize(Size.values()[index-1]);
		} while(index<0 || index>Pizza.Size.values().length);
			
		
		do {
			index=1;
			System.out.println("Please choose your dough");
			for(Pizza.Dough d:Pizza.Dough.values()) {
				System.out.println(index++ +"- "+d);
			}
			index=sc.nextInt();
			pizza.setDough(Dough.values()[index-1]);
		} while(index<0 || index>Pizza.Dough.values().length);
		pizza.setDough(Dough.values()[index-1]);

		return pizza;
	}
	

	private void chooseDeliveryTime(Order order) {
		int hours,minutes;
		do {
		System.out.println("Please insert hours");
			 hours=sc.nextInt();
				System.out.println("Please insert minutes");
			 minutes=sc.nextInt();
		}
		while(hours<MIN_HOURS || hours>MAX_HOURS || minutes<MIN_MINUTES || minutes>MAX_MINUTES);	
		try {
			order.insertDate(LocalTime.of(hours, minutes));
		} catch (InvalidTimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	private void chooseAddress(User user, Order order) throws InvalidAddress {
		int command=0;
			do {
			System.out.println("1-Add an address"+"\n"+"2-Choose an Adress");
			command=sc.nextInt();
			switch (command) {
			case 1:
				Menu.getMenu().insertAddress(user);
			case 2:
				if(user.getAddresses().size()==0) {
					System.out.println("You got no addresses added");
					Menu.getMenu().insertAddress(user);
				}
				
				int chooseIndex;
				do {
					int displayIndex=STARTING_INDEX;
					System.out.println("Please choose an address");
					for(String s:user.getAddresses()) {
						System.out.println("Address "+displayIndex++ +"- "+s);
					}
					chooseIndex=sc.nextInt();
				}while(chooseIndex<0 || chooseIndex>=user.getAddresses().size());
				order.setAddress(user.getAddresses().get(chooseIndex));
				return;
			default:
				break;
			}
		}while(command!=1 || command!=2);
	}

	private void insertAddress(User user) throws InvalidAddress {
		System.out.println("Please insert an address");
		sc.nextLine();
		user.addAddress(sc.nextLine());
	}
	
	private static Pizza createYourOwnPizza()  {
		Pizza pizza = null;
		try {
			pizza =new Pizza("Your pizza",0);
		} catch (InvalidProductException e) {
			e.printStackTrace();
		} catch (InvalidPriceException e) {
			e.printStackTrace();
		}
		
		pizza=Menu.getMenu().chooseIngredients(pizza);
		return pizza;
	}
	
	public Pizza chooseIngredients(Pizza pizza) {
		int index=-1;
		while(index!=0) {
			System.out.println("Choose your ingredients please ");
			List<Ingredient> ingredients=IngredientsStorage.getIngredientsStorage().listAllIngredientsWithIndexes();
			index=sc.nextInt();
			if(index<0 || index>ingredients.size()) {
				System.out.println("Invalid index");
				continue;
			}
			Ingredient i=ingredients.get(index-1);
			pizza.addIngredient(i);
			System.out.println("Successfully added "+i);
			System.out.println("Press 0 if u have finished with your ingredients or any other number if u want more ingredients");
			index=sc.nextInt();	
		}
		return pizza;
	}
	
	//----------------------------------------------------------------
	
	private User showLoginVerification() {
		System.out.println("-------------------------------- \nLOGIN");
		System.out.println("Please enter your e-mail!");
		String eMail = sc.next();
		System.out.println("Please enter your password!");
		String password = sc.next();
		
		try {
		 	User user = UserStorage.getUserStorage().login(eMail, password);
		 	return user;
		} catch (InvalidEMailException | WrongPasswordException e) {
			System.out.println("Invalid input");
			return null;
		}
		
	}
	
	
	private void showRegistrationForm() {
		System.out.println("Please enter your e-mail!");
		String eMail = sc.next();
		System.out.println("Please enter your name!");
		String name = sc.next();
		System.out.println("Please enter your phone number!");
		String phoneNumber = sc.next();
		System.out.println("Please enter your password!");
		String password = sc.next();
		
		try {
			UserStorage.getUserStorage().addNewUser(name, phoneNumber, eMail, password);
		} catch (EmailAlreadyExistException | InvalidDataExcecption e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean isValidChoice(List<Product> products, short choice) {
		return choice>=0 && choice < products.size();
	}
}
