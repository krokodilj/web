package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import dao.SnippetDAO;
import model.Comment;
import model.Snippet;

@Path("/snippets")
public class SnippetService {

	private SnippetDAO dao = new SnippetDAO();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSnippet(Snippet s){
		
		String id=dao.addSnippet(s);
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
	
	@PUT
	@Path("/{id}")
	public Response lockSnippet(@PathParam("id") String id){
		if(dao.lockSnippet(id))return Response.ok().build();
		
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
		
		if(dao.addLanguage(l))	return Response.ok().build();
		
		return Response.status(Response.Status.CONFLICT).build();
	}

	@POST
	@Path("/{snippet_id}/comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("snippet_id") String snippetId,Comment c){
		
		if(dao.addComment(snippetId,c)){
			return Response.ok().build();
		}
		return Response.status(Response.Status.CONFLICT).build();
	}
	
	@DELETE
	@Path("/{snippet_id}/comment/{comment_id}")
	public Response deleteComment(@PathParam("snippet_id") String snippetId,@PathParam("comment_id") String commentId){
		
		//proveri token
		
		if(dao.deleteComment(snippetId, commentId))
			return Response.ok().build();
		
		return Response.status(Response.Status.CONFLICT).build();
	}
	
	@POST
	@Path("/{snippet_id}/comment/{comment_id}/grade")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrade(
			@PathParam("snippet_id") String snippetId,
			@PathParam("comment_id") String commentId,
			JSONObject data){
		
		if(dao.addGrade(snippetId,commentId,(String)data.get("user"),(boolean) data.get("grade"))){
			return Response.ok().build();
		}
		
		return Response.status(Response.Status.CONFLICT).build();
		
	}
	
}
