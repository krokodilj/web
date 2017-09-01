package model;

import java.util.Date;

public class Comment {
	
	private String id;
	private String owner;
	private String owner_img;
	private String text;
	private Grade grade;
	private Date date;
	
	public Comment(){
		
	}

	public Comment(String id,String owner,String owner_img, String text, Grade grade,Date date) {
		super();
		this.id=id;
		this.owner = owner;
		this.owner_img = owner_img;
		this.text = text;
		this.grade = grade;
	}

	public String getOwner_img() {
		return owner_img;
	}

	public void setOwner_img(String owner_img) {
		this.owner_img = owner_img;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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
}
