package userSystem;

public class Demo {

	public static void main(String[] args) {
		
		while(true) {
			try {
				Menu.getMenu().showMenu();
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}

}
