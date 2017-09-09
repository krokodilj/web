package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Comment;
import model.Grade;
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
		
		file = new File("data"+File.separator+"snippets.json");
		file1 = new File("data"+File.separator+"languages.json");
		try {		
			 snippets = mapper.readValue(file, Snippets.class);
			 languages = mapper.readValue(file1, List.class);
			
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
			s.setLocked(false);
			snippets.data.put(s.getId(),s);
			saveData();
			return id;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public Snippet getSnippet(String id){
		return snippets.data.get(id);
	}
	
	public List<Snippet> getByOwner( String username){
		List<Snippet> ls=new ArrayList<Snippet>();
		for(Snippet s:snippets.data.values()){
			if(s.getOwner().equals(username)) ls.add(s);
		}
		return ls;
		
	}
	
	public List<Snippet> search(String desc,String language,Long s , Long e){
		
		List<Snippet> in;
		List<Snippet> result= new ArrayList<Snippet>();
		for(Snippet ss:snippets.data.values()) result.add(ss);
		
		if(desc!=null){
			in=copyList(result);
			result.clear();
			for(Snippet snippet:in){
				if(snippet.getDesc().contains(desc))
					result.add(snippet);
			}
			
		}
		
		if(language!=null){
			in=copyList(result);
			result.clear();
			for(Snippet snippet:in){
				if(snippet.getLanguage().equals(language))
					result.add(snippet);
			}
		}
		
		if(s!=null){
			Date start=new Date(s);
			in=copyList(result);
			result.clear();
			for(Snippet snippet:in){
				if(start.before(snippet.getDate()))
					result.add(snippet);
			}
			
		}
		
		if(e!=null){
			Date end=new Date(e);
			in=copyList(result);
			result.clear();
			for(Snippet snippet:in){
				if(end.after(snippet.getDate()))
					result.add(snippet);
			}
		}
		
		return result;
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
	
	public boolean addComment(String snippetId,Comment c){
		Snippet s=snippets.data.get(snippetId);
		
		if(s.isLocked()) return false;
		
		String id=IdentificatorGenerator.generateId();
		c.setId(id);
		c.setDate(new Date());
		c.setGrade(new Grade());
		s.getComments().add(c);
		
		saveData();
		
		return true;	
		
	}
	
	public boolean deleteComment(String snippetId,String commentId){
		Snippet s=snippets.data.get(snippetId);
		
		int idx=-1;
		for(int i=0;i <s.getComments().size();i++){
			if(s.getComments().get(i).getId().equals(commentId)) idx=i;
		}
		
		if(idx<0) return false;
		
		s.getComments().remove(idx);
		saveData();
		return true;
		
	}
	
	public boolean lockSnippet(String id){
		if(!snippets.data.containsKey(id)) return false;
		
		snippets.data.get(id).setLocked(!snippets.data.get(id).isLocked());
		
		saveData();
		
		return true;
	}
	public boolean addGrade(String snippetId,String commentId,String userId,boolean grade){
		if(!snippets.data.containsKey(snippetId)) return false;
		
		Snippet s=snippets.data.get(snippetId);
		
		int ind=-1;
		
		for(int i=0;i<s.getComments().size();i++){
			if(s.getComments().get(i).getId().equals(commentId)) {
				ind=i;
				
			}
		}
		if(ind==-1) return false;
		
		
		//sad dodaj ocenu
		s.getComments().get(ind).getGrade().raise(grade);
		s.getComments().get(ind).getGrade().getUsers().add(userId);
		
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
	
	private List<Snippet> copyList(List<Snippet> in){
		List<Snippet> result=new ArrayList<Snippet>();
		
		for(Snippet s:in) result.add(s);
		
		return result;
	}
	
}
