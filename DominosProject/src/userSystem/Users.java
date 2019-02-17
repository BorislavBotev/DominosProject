package userSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("users")
public class Users {
	private static List<User> userList=new ArrayList<User>();
	
	public static void addUser(User u) {
		System.out.println(u);
		if(u!=null) {
			userList.add(u);
			try {
				new XStream().toXML(userList,new PrintWriter(new File("data_base"+File.separator+"Users.xml")));
			} catch (FileNotFoundException e) {
				
			}
		}
	}

	public List<User> getUsers() {
		return userList!=null?Collections.unmodifiableList(userList):Collections.emptyList();
	}
}
