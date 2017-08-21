package model;

import java.util.HashMap;

public class Users {
	public HashMap<String,User> data =new HashMap<String,User>();;
	
	public Users(){
		
	}

	public HashMap<String, User> getData() {
		return data;
	}

	public void setData(HashMap<String, User> data) {
		this.data = data;
	}
}
