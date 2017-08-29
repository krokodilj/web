package model;

import java.util.Date;
import java.util.List;

public class Snippet {
	
	private String id;
	private String owner;
	private String desc;
	private String code;
	private String language;
	private String url;
	private Date date;
	private long minutes;
	private List<Comment> comments;
	private boolean locked ;
	
	public Snippet(){
		
	}

	public Snippet(String id,String owner, String desc, String code, String language, String url,
			Date date, long minutes,	List<Comment> comments,boolean locked) {
		super();
		this.id=id;
		this.owner = owner;
		this.desc = desc;
		this.code = code;
		this.language = language;
		this.url = url;
		this.date=date;
		this.minutes = minutes;
		this.comments = comments;
		this.locked=locked;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getMinutes() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Snippet copy(){
		return new Snippet(this.id,this.owner,this.desc,this.code,this.language,this.url,this.date,this.minutes,
		this.comments,this.locked);
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
