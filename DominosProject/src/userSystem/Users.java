package userSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("users")
public class Users {
	private List<User> userList=new ArrayList<User>();
	

	public List<User> getUsers() {
		return userList!=null?Collections.unmodifiableList(userList):Collections.emptyList();
	}
}
