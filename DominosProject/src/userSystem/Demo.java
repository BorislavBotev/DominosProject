package userSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;

import exceptions.InvalidAddress;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException, InvalidAddress {
//		User u=new User("gosho", "0888975865", "abv", "1234");
//		u.addAddress("123");
//		u.addAddress("234");
//		
//		new XStream().toXML(UserStorage.getUserStorage().getUsers(),new PrintWriter(new File("data_base"+File.separator+"Users.xml")));
		try {
			Menu.getMenu().showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
