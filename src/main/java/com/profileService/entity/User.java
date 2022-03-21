package com.profileService.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Document(collection = "Usuarios")
@Data
public class User {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String status;
	private String lastname;
	private Rol rol;
	private String imageProfile;
	private byte[] imageProfileBytes;
	private String age;
	private String country;
	private String type;
	private String[] interests;
	private double distance;
	private String[] favourites;


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", status="
				+ status + ", lastname=" + lastname + ", rol=" + rol + ", imageProfile=" + imageProfile + ", age="
				+ age + ", country=" + country + ", type=" + type + ", interests=" + interests + ", distance=" + distance
				+ ", favourites=" + favourites + "]";
	}

	
}
