package userSystem;

public abstract class Product {
	private double price;
	private String name;
	private String photo;
	public Product(String name,double price) throws InvalidProductException, InvalidPriceException {
		if(name==null || name.trim().length()==0) {
			throw new InvalidProductException();
		}
		this.name = name;
		this.setPrice(price);
		this.photo="";
	}
	public void setPrice(double price) throws InvalidPriceException {
		if(price <0) {
			throw new InvalidPriceException();
		}
		this.price = price;
	}

	 
}
