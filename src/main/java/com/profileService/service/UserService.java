package com.profileService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.profileService.entity.User;
import com.profileService.model.Response;
import com.profileService.model.ResponseUser;
import com.profileService.model.UpdateUser;
import com.profileService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/// METODO CREAR USUARIO
	public Response saveUser(User user) {
		System.out.print(user);
		Response response = new Response();
		User userUpdate = new User();
		userUpdate = userRepository.findByEmail(user.getEmail());
		try {
			
			if(userUpdate == null){
				
				user.setPassword(encoder().encode(user.getPassword()));
				userRepository.save(user);
				response.setCode("0");
				response.setMessage("El usuario " + user.getName() + " se creo con exito");
			}else{
				response.setCode("-1");
				response.setMessage("El usuario ya existe");
			}
		

		} catch (Exception e) {
			response.setCode("-1");
			response.setMessage("Ocurrio un error de excepcion: " + e);

		}

		return response;

	}


	//METODO ACTUALIZAR USUARIO
	public ResponseUser updateUser(UpdateUser user) {

		ResponseUser response = new ResponseUser();
		User userUpdate = new User();
		userUpdate = userRepository.findByEmail(user.getEmail());

		try {
			if (userUpdate != null) {
				List<User> lista = new  ArrayList<User>();
				userUpdate.setName(user.getName());
				userUpdate.setLastname(user.getLastname());
				userRepository.save(userUpdate);
				response.setCode("0");
				response.setMessage("Se actualizo con exito");
				lista.add(userUpdate);
				response.setUser(lista);
			} else {
				response.setCode("-1");
				response.setMessage("No actualizo");
				response.setUser(null);
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio una exception de " + e.getMessage());
			response.setUser(null);
		}

		return response;
	}

	
	//BUSCAR USUARIO POR CORREO
	public ResponseUser findUser(String email){
	
		ResponseUser response = new ResponseUser();
		User user = new User();
		try {
			user = userRepository.findByEmail(email);
			if(user != null){
			List<User> lista = new  ArrayList<User>();
			response.setCode("0");
			response.setMessage("exito en la consulta de: " +email);
			lista.add(user);
			response.setUser(lista);		
			}else{
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}
			
		} catch (Exception e) {		
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " +e.getMessage());
		}	
		return response;
	}

	
	
	//INACTIVAR USUARIO
	public Response deleteUser(String email) {

		Response response = new Response();
		User userDelete = new User();
		try {

			userDelete = userRepository.findByEmail(email);
			if(userDelete != null){
				userDelete.setStatus("I");
				userRepository.save(userDelete);
				response.setCode("0");
				response.setMessage("Se elimino el usuario "+userDelete.getName());
			}else{
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}
			

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " +e.getMessage());
		}
		return response;

	}
	
	
	
	public Response activeUser(String email) {

		Response response = new Response();
		User userDelete = new User();
		try {

			userDelete = userRepository.findByEmail(email);
			if(userDelete != null){
				userDelete.setStatus("A");
				userRepository.save(userDelete);
				response.setCode("0");
				response.setMessage("Se activo el usuario "+userDelete.getName());
			}else{
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}
			

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " +e.getMessage());
		}
		return response;

	}
	
	
	public ResponseUser findAllUser(){
		
		ResponseUser response = new ResponseUser();
		List<User> lista = new  ArrayList<User>();
		try {
			lista = userRepository.findAll();
			if(lista != null){
			
			response.setCode("0");
			response.setMessage("exito en la consulta de: ");
			response.setUser(lista);		
			}else{
				response.setCode("-1");
				response.setMessage("No hay registros");
			}
			
		} catch (Exception e) {		
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " +e.getMessage());
		}	
		return response;
	}
	
	
	
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	

}
