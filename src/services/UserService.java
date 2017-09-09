package services;

import java.io.InputStream;
import java.util.List;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import dao.UserDAO;
import model.Snippet;
import model.User;

@Singleton
@Path("/users")
public class UserService {

	private UserDAO dao;
	@Context ServletContext ctx;
	
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
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload(@FormDataParam("username") String username,@FormDataParam("file") InputStream file){
				
		if(dao.uploadPicture(username,file,ctx.getRealPath("images/"))) {
				return Response.ok().build();			
		}
			
		return Response.status(Response.Status.CONFLICT).build();		
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
	
	@GET
	@Path("/by_username/{query}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByDesc(@PathParam("query") String query){
		
		List<User> s =dao.getByUsername(query);
		return Response.ok().entity(s).build();
		
	}
	
	@PUT
	@Path("block/{user_id}")
	public Response blockUser(@PathParam("user_id") String userId){
		
		//samo admin token
		
		if(dao.blockUser(userId))
			return Response.ok().build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
		
}
