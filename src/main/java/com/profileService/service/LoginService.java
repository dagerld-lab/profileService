package com.profileService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.profileService.entity.User;
import com.profileService.model.ResponseUser;
import com.profileService.model.loginRequest;
import com.profileService.repository.UserRepository;

@Service
public class LoginService {

	
	@Autowired
	UserRepository userRepository;
	
	public ResponseUser Login(loginRequest request){
		ResponseUser response = new ResponseUser();
		try{
			
			
			User userUpdate = new User();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userUpdate = userRepository.findByEmail(request.getEmail());
			
			if(userUpdate !=null){
				boolean pass = encoder.matches(request.getPassword(), userUpdate.getPassword());
				if(pass){
					response.setCode("0");
					response.setMessage("exito al en login");
					List<User> lista = new  ArrayList<User>();
					userUpdate.setPassword(null);
					lista.add(userUpdate);
					
					response.setUser(lista);
				}else{
					response.setCode("-1");
					response.setMessage("Crendenciales incorrectas");
					response.setUser(null);
				}
				
				
			}else{
				response.setCode("-1");
				response.setMessage("Crendenciales incorrectas");
				response.setUser(null);
			}
			
			
		}catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un problema: " +e.getMessage() );
			response.setUser(null);
		}
		
		
		return response;
		
	}
	
	public PasswordEncoder encoder(){
		try{
			return new BCryptPasswordEncoder();
			
		}catch (Exception e) {
			return null;
		}
		
	}
	

}
