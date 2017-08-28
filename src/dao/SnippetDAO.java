package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Comment;
import model.Snippet;
import model.Snippets;
import util.IdentificatorGenerator;

public class SnippetDAO {

	private File file;
	private File file1;
	private ObjectMapper mapper;
	private Snippets snippets= new Snippets();
	private List<String> languages=new ArrayList<String>();
	public SnippetDAO(){
		
		mapper= new ObjectMapper();
		
		File dir = new File("./data");
		if (!dir.exists()) dir.mkdir();
		
		file = new File("./data/snippets.json");
		file1 = new File("./data/languages.json");
		try {		
			if(file.exists()) snippets = mapper.readValue(file, Snippets.class);
			if(file1.exists()) languages = mapper.readValue(file1, List.class);
			else{
				file.createNewFile();
				saveData();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addSnippet(Snippet s){
		try{
			String id=IdentificatorGenerator.generateId();
			s.setId(id);
			s.setDate(new Date());
			s.setComments(new ArrayList<Comment>());
			snippets.data.put(s.getId(),s);
			saveData();
			return id;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public Snippet getSnippet(String id){
		return snippets.data.get("q");
	}
	
	public List<Snippet> getByOwner( String username){
		List<Snippet> ls=new ArrayList<Snippet>();
		for(Snippet s:snippets.data.values()){
			if(s.getOwner().equals(username)) ls.add(s);
		}
		return ls;
		
	}
	
	public List<String> getLanguages(){
		return languages;
	}
	
	public boolean addLanguage(String l){
		try{
			 if(languages.contains(l)) return false;
			 languages.add(l);
			 saveData();
			 return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteSnippet(String id){
		if(!snippets.data.containsKey(id)) return false;
		
		snippets.data.remove(id);
		
		saveData();
		
		return true;		
	}
	
	private void saveData(){
		try {
			mapper.writeValue(file, snippets);
			mapper.writeValue(file1, languages);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
