package com.profileService.model;

import java.util.List;

import com.profileService.entity.User;

public class ResponseUser {
	
	public String code;
	public String message;
	public List<User> user;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ResponseUser [code=" + code + ", message=" + message + ", user=" + user + "]";
	}

	
	
	

}
