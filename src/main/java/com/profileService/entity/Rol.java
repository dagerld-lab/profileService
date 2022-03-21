package com.profileService.entity;

import lombok.Data;

@Data
public class Rol {
	
     private String code;
     private String name;
   
	public Rol() {
		super();
	}

	@Override
	public String toString() {
		return "Rol [code=" + code + ", name=" + name + "]";
	} 
	
}
