package com.profileService.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Usuarios")
public class User {
	
	@Id
	public String id;
	//public String identification;
	public String name;
	public String email;
	public String password;
	public String status;
	public String lastname;
	public Rol rol;
	
	
	
	
	public User() {
		super();
	}

	



	public User(String id, String name, String email, String password, String status, String lastname, Rol rol) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.lastname = lastname;
		this.rol = rol;
	}





	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public String getIdentification() {
//		return identification;
//	}
//	public void setIdentification(String identification) {
//		this.identification = identification;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}

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

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", status="
				+ status + ", lastname=" + lastname + ", rol=" + rol + "]";
	}


	
}
