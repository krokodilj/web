package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.SnippetDAO;
import model.Snippet;

@Path("/snippets")
public class SnippetService {

	private SnippetDAO dao = new SnippetDAO();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSnippet(Snippet s){
		
		String id=dao.addSnippet(s);
		System.out.println(id.length());
		if(id.length()==33){
		
			return Response.ok().entity(id).build();
		}
		
		return Response.status(Response.Status.CONFLICT).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSnippet(@PathParam("id") String id){
		
		Snippet s=dao.getSnippet(id);
		
		return Response.ok().entity(s).build();
	}
	
	@GET
	@Path("/by_owner/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByOwner(@PathParam("username") String username){
		
		List<Snippet> s=dao.getByOwner(username);
		
		return Response.ok().entity(s).build();
		
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteSnippet(@PathParam("id") String id){
		//token 
		
		if(dao.deleteSnippet(id)){
			return Response.ok().build();
		}
		return Response.status(Response.Status.CONFLICT).build();
	}
	
	@GET
	@Path("/languages")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLanguages(){
		
		List<String>ls=dao.getLanguages();
		return Response.ok().entity(ls).build();
	}
	
	@POST
	@Path("/languages")
	public Response addLanguage(String l){
		System.out.println(l);
		
		if(dao.addLanguage(l))	return Response.ok().build();
		
		return Response.status(Response.Status.CONFLICT).build();
	}
	
}
