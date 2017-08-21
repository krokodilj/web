package dao;

import java.io.File;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.Users;

public class UserDAO {

	private File file;
	private ObjectMapper mapper;
	private Users users= new Users();
	
	public UserDAO(){
		
		mapper= new ObjectMapper();
		
		File dir = new File("./data");
		if (!dir.exists()) dir.mkdir();
		
		file = new File("./data/users.json");

		try {		
			if(file.exists()) users = mapper.readValue(file, Users.class);
			else{
				file.createNewFile();
				saveData();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isUsernameOk(String username){
		
		if(users.data.containsKey(username)) return false;		
		return true;
	}
	
	public boolean register(User usr){
		try{
			usr.setRole("usr");
			usr.setBlocked(false);
			users.data.put(usr.getUsername(), usr);
			saveData();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loginCheck(String usrname, String pass){
		try{
			if(! isUsernameOk(usrname))
			if(users.data.get(usrname).getPassword().equals(pass))return true;			
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public JSONObject login(String username){
		JSONObject token=new JSONObject();
		token.put("name", username);
		token.put("role", users.data.get(username).getRole());
		return token;
	}
	
	private void saveData(){
		try {
			mapper.writeValue(file, users);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
}
