package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;

@Provider
public class Loader implements ServletContextListener {

   

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("APPSHUTDOWN");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("APPSTARTUP");
		
		File dir = new File("./data");
		if (!dir.exists()) dir.mkdir();
		
		File imdir = new File("./data/images");
		if (!imdir.exists()) imdir.mkdir();
		
		
		String from_path="./data/images";
		String to_path=arg0.getServletContext().getRealPath("images");
		
		for(File f:imdir.listFiles()){
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
