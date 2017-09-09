package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Snippets;
import model.Users;

@Provider
public class Loader implements ServletContextListener {

   

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("APPSHUTDOWN");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("APPSTARTUP");
		
		//initialize data directory if does not exist
		
		File dir = new File("data");
		if (!dir.exists()) {
			System.out.println("data directory not found...");
			System.out.println("initializing data directory...");
			dir.mkdir();
			File imdir = new File("data"+File.separator+"images");
			if (!imdir.exists()) imdir.mkdir();
			
			//init datafiles
			ObjectMapper mapper= new ObjectMapper();
			
			System.out.println("initializing datafile > users.json");
			//user.json
			File usrsfile = new File(dir.getPath()+File.separator+"users.json");
			try{
				usrsfile.createNewFile();
				
				//add admins
				Users users= new Users();			
				
				
				mapper.writeValue(usrsfile, users);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			System.out.println("initializing datafile > languages.json");
			//languages.json
			File lngfile = new File(dir.getPath()+File.separator+"languages.json");
			try{
				lngfile.createNewFile();
				
				System.out.println("creating default language ~ plaintext");
				List<String> languages=new ArrayList<String>();
				languages.add("plaintext");								
				mapper.writeValue(lngfile, languages);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("initializing datafile > snippets.json");
			//languages.json
			File snptfile = new File(dir.getPath()+File.separator+"snippets.json");
			try{
				
				snptfile.createNewFile();								
				mapper.writeValue(snptfile, new Snippets());
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}else{		
			System.out.println("copying images to server static folder...");
			File imdir = new File("data"+File.separator+"images");			
			String to_path=arg0.getServletContext().getRealPath("images");
			
			
			for(File f:imdir.listFiles()){
				File ff=new File(to_path+File.separator+f.getName());
				if(!ff.exists()){
					try{
						int read = 0;
			            byte[] bytes = new byte[1024];
			
			            InputStream is=new FileInputStream(f);
			            
			            OutputStream  outpuStream = new FileOutputStream(new File(to_path+File.separator+f.getName()));
			            while ((read = is.read(bytes)) != -1) {
			                outpuStream.write(bytes, 0, read);
			            }
			            is.close();
			            outpuStream.flush();
			            outpuStream.close();
		            
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
		
	}
	
	
	
	
	
	
}
