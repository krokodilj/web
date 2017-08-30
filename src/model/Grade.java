package model;

import java.util.ArrayList;
import java.util.List;

public class Grade {
	
	private int positive=0;
	private int negative=0;
	private List<String> users=new ArrayList<String>();
	
	public Grade(){
		
	}
	
	public void raise(boolean grade){
		if(grade) {
			this.positive++;
		}
		else{
			this.negative++;
		}
	}
	
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getNegative() {
		return negative;
	}
	public void setNegative(int negative) {
		this.negative = negative;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
}
