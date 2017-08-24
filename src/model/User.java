package model;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String role;
	private String fname;
	private String lname;
	private String address;
	private String phone;
	
	private boolean blocked;
	private String img_src;
	
	public User(){
		
	}

	public User(String username, String password, String email, String role, String fname, String lname, String address,
			String phone, boolean blocked, String img_src) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.blocked = blocked;
		this.img_src = img_src;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public User copy(){
		return new User(this.username,this.password,this.email,this.role,this.fname,
				this.lname,this.address,this.phone,this.blocked,this.img_src);
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", fname=" + fname
				+ ", lname=" + lname + ", address=" + address + ", phone=" + phone + ", blocked=" + blocked
				+ ", img_src=" + img_src + "]";
	}
	
}
