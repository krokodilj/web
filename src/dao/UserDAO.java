package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Snippet;
import model.User;
import model.Users;

public class UserDAO {

	private File file;
	private ObjectMapper mapper;
	private Users users= new Users();
	
	public UserDAO(){
		
		mapper= new ObjectMapper();
		file= new File("data"+File.separator+"users.json");
		try {		
			users = mapper.readValue(file, Users.class);			
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
			usr.setRole("ruser");
			usr.setBlocked(false);
			usr.setImg_src("images/guest.png");
			users.data.put(usr.getUsername(), usr);
			saveData();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean uploadPicture(String username,InputStream fis,String path){
		try {			
			System.out.println(path);
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream  outpuStream = new FileOutputStream(new File(path+File.separator+username+".png"));
            while ((read = fis.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            fis.close();
            outpuStream.flush();
            outpuStream.close();
            
            InputStream is=new FileInputStream(new File(path+File.separator+username+".png"));
            
            read = 0;
            bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File("./data/images"+File.separator+username+".png"));
            while ((read = is.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            is.close();
            outpuStream.flush();
            outpuStream.close();
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        	return false;            
        }
		
		users.data.get(username).setImg_src("images/"+username+".png");
		saveData();
		return true;
	}
	
	public boolean loginCheck(String usrname, String pass){
		try{
			if(! isUsernameOk(usrname))
			if(users.data.get(usrname).getPassword().equals(pass))
			if(!users.data.get(usrname).isBlocked())return true;	
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
		token.put("img_src", users.data.get(username).getImg_src());
		return token;
	}
	
	public User getUser(String username){
		User u = users.data.get(username).copy();	
		u.setPassword(null);
		return u;
	}
	
	public List<User> getByUsername(String query){
		List<User> ls=new ArrayList<User>();
				
		for(User s:users.data.values()){
			if(s.getUsername().contains(query)||(query.equals("undefined"))) ls.add(s);
		}
		return ls;
		
	}
	
	public boolean blockUser(String id){
		if(!users.data.containsKey(id)) return false;
		
		users.data.get(id).setBlocked(true);
		
		saveData();
		
		return true;
	}
	
	private void saveData(){
		try {
			mapper.writeValue(file, users);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
}
