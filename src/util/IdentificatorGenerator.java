package util;

import java.security.SecureRandom;

public class IdentificatorGenerator {
	
	static final String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom random = new SecureRandom();
	
	public static String generateId(){
		  StringBuilder sb = new StringBuilder(33);
		  for( int i = 0; i < 33; i++ ) 
		      sb.append( charSet.charAt( random.nextInt(charSet.length()) ) );
		  return sb.toString();
		
	}
	
}
