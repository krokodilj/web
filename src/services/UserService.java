package services;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import dao.UserDAO;
import model.User;

@Singleton
@Path("/users")
public class UserService {

	private UserDAO dao;
	
	public UserService(){
		dao = new UserDAO();
	}
		
	@GET
	@Path("/check/{username}")
	public Response checkUsername(@PathParam("username") String username){
		
		if(dao.isUsernameOk(username)){
			return Response.ok().build();
		}
		return Response.status(Response.Status.CONFLICT).build();
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User usr){	
		if(dao.register(usr)){
			return Response.ok().build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User usr){
		if(dao.loginCheck(usr.getUsername(),usr.getPassword())){
			
			JSONObject token= dao.login(usr.getUsername());
			
			return Response.ok().entity(token).build();
		}
		return Response.status(401).build();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("username") String username,@Context HttpHeaders headers){
		
		//check for token
	
		if(!dao.isUsernameOk(username)){
			User u=dao.getUser(username);
			return Response.ok().entity(u).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
		
}
