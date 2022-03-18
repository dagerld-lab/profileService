package com.profileService.entity;

public class Rol {
	
	
     public String code;
     public String name;
     
     
     
	public Rol() {
		super();
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Rol [code=" + code + ", name=" + name + "]";
	}
     
     
	
}
