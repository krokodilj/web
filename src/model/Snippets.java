package model;

import java.util.HashMap;

public class Snippets {

	public HashMap<String,Snippet> data =new HashMap<String,Snippet>();;
	
	public Snippets(){
		
	}

	public HashMap<String, Snippet> getData() {
		return data;
	}

	public void setData(HashMap<String, Snippet> data) {
		this.data = data;
	}
	
}
