package userSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;

import exceptions.InvalidAddress;

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
