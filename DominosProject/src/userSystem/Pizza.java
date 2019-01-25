package userSystem;

public class Pizza extends Product {
	private enum Size {
		MEDIUM(6),LARGE(8),DJUMBO(12);
		private int numberOfPieces;
		Size(int num){
			this.numberOfPieces=num;
		}
		public int getNumberOfPieces() {
			return numberOfPieces;
		}
		
		
	}
	private Size size;
	
	public Pizza(String name, double price) throws InvalidProductException, InvalidPriceException {
		super(name, price);
	}

	public Pizza(String name, double price,Size size) throws InvalidProductException, InvalidPriceException {
		super(name, price);
		this.size=size;
	}
	public void addIngredients() {
		
	}
	public void removeIngredients() {
		
	}
	
}
