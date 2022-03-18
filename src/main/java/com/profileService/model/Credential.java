package com.profileService.model;

public class Credential {
	public String email;
	public String password;
	
	
	
	
	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "Credential [email=" + email + ", password=" + password + "]";
	}
	
	

}
