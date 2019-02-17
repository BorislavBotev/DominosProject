package userSystem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidPersonException;

public class EmployeeStorage {
	private static EmployeeStorage employeeStorage = null;
	private Set<DeliveryGuy> deliveryGuys;
	private Set<Cooker> cookers;
	
	private EmployeeStorage() {
		this.cookers = new HashSet<Cooker>();
		this.deliveryGuys = new HashSet<DeliveryGuy>();
		this.cookers.add(new Cooker("Gichka", "0894738952"));
		this.cookers.add(new Cooker("Penka", "0892572952"));
		this.deliveryGuys.add(new DeliveryGuy("Mitko", "0889238952"));
		this.deliveryGuys.add(new DeliveryGuy("Peio", "0874734582"));
	}
	
	public static EmployeeStorage getemployeeStorage() {
		if(EmployeeStorage.employeeStorage == null) {
			employeeStorage = new EmployeeStorage();
		} 
		return employeeStorage;
	}
	
	public void addDeliveryGuy(DeliveryGuy deliveryGuy) throws InvalidPersonException {
		if(deliveryGuy!=null) {
			this.deliveryGuys.add(deliveryGuy);
		} else throw new InvalidPersonException("Invalid Delivery Guy!");
	}
	
	public void addCooker(Cooker cooker) throws InvalidPersonException {
		if(cooker!=null) {
			this.cookers.add(cooker);
		} else throw new InvalidPersonException("Invalid Cooker!");
	}
	
	public Set<Cooker> getCookers(){
		return Collections.unmodifiableSet(this.cookers);
	}
	
	public Set<DeliveryGuy> getDeliveryGuys(){
		return Collections.unmodifiableSet(this.deliveryGuys);
	}
}
