package userSystem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import exceptions.InvalidChoiceException;
import exceptions.InvalidProductException;

public class Order {
	private static final int INITIAL_QUANTITY = 1;
	private User client;
	private Map<Product,Integer> products;
	private double price;
	private DeliveryGuy deliveryGuy;
	private LocalTime time;
	private boolean isFinalized;
	

	public Order(User client) {
		this.client = client;
	}

	public void addProduct(Product product) throws InvalidProductException {
		if(product==null) {
			throw new InvalidProductException("Invalid product given");
		}
		if(!products.containsKey(product)) {
			products.put(product, INITIAL_QUANTITY);
		}
		else {
			int count=products.get(product);
			products.put(product, ++count);	
		}
		price+=product.getPrice();
	}
	
	public void addDeliveryGuy(DeliveryGuy deliveryGy) {
		
	}
	
	
	public void removeProduct() throws InvalidChoiceException {
		if(this.products.size()==0) {
			System.out.println("The Bucket is empty!");
			return;
		}
		List<Product> products = new ArrayList<Product>(this.products.keySet());
		for(byte index=0; index<products.size(); index++) {
			System.out.println((index+1) + " - " + products.get(index));
		}
		Scanner sc = new Scanner(System.in);
		byte choice = (byte) (sc.nextByte()-1);
		if(choice>=0 && choice<products.size()) {
			Product productToRemove = products.get(choice);
			if(this.products.containsKey(productToRemove)) {
				if(this.products.get(productToRemove)==Order.INITIAL_QUANTITY) {
					this.products.remove(productToRemove);
				} else {
					int count=this.products.get(productToRemove);
					this.products.put(productToRemove, --count);
				}
				System.out.println(productToRemove + " was successfully removed!");
			}
		} else throw new InvalidChoiceException("Invalid Choice!");
	}
	
	
	public void listOrder() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		StringBuilder order=new StringBuilder();
		order.append("Client "+client.getName()+"\n");
		for(Entry<Product,Integer> entry:products.entrySet()) {
			order.append(entry.getValue()+" X "+ entry.getValue()+"\n");
		}
		order.append("Your delivery guy "+deliveryGuy.getName()+"\n");
		order.append("Your order is set for "+time+"\n");
		return order.toString();
		
	}

	public boolean isFinalized() {
		return isFinalized;
	}

	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

}
