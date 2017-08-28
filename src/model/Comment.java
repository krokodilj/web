package model;

import java.util.Date;

public class Comment {
	
	private String id;
	private String owner;
	private String text;
	private long positive;
	private long negative;
	private Date date;
	
	public Comment(){
		
	}

	public Comment(String id,String owner, String text, long positive, long negative,Date date) {
		super();
		this.id=id;
		this.owner = owner;
		this.text = text;
		this.positive = positive;
		this.negative = negative;
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

	public long getPositive() {
		return positive;
	}

	public void setPositive(long positive) {
		this.positive = positive;
	}

	public long getNegative() {
		return negative;
	}

	public void setNegative(long negative) {
		this.negative = negative;
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
