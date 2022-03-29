package com.profileService.model;

import lombok.Data;

@Data
public class loginRequest {
	
	
	private String email;	
	private String password;
	private String type;
	private String device;
	
}
