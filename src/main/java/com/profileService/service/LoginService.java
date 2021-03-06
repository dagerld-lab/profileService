package com.profileService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.profileService.entity.User;
import com.profileService.enums.AuthType;
import com.profileService.model.ResponseUser;
import com.profileService.model.loginRequest;
import com.profileService.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;

	public ResponseUser Login(loginRequest request) {
		ResponseUser response = new ResponseUser();
		try {

			User userUpdate = new User();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userUpdate = userRepository.findByEmail(request.getEmail());

			if (userUpdate != null) {
				if (request.getType().equalsIgnoreCase(userUpdate.getType())) {
					if (userUpdate.getType().equalsIgnoreCase(AuthType.EMAIL.name())) {
						boolean pass = encoder.matches(request.getPassword(), userUpdate.getPassword());
						if (pass) {

							List<User> lista = new ArrayList<User>();
							if (!userUpdate.getDevices().contains(request.getDevice())) {
								userUpdate.getDevices().add(request.getDevice());
								userRepository.save(userUpdate);
							}
							userUpdate.setPassword(null);
							lista.add(userUpdate);
							response.setUser(lista);
							response.setCode("0");
							response.setMessage("exito al en login");
						} else {
							response.setCode("-1");
							response.setMessage("Crendenciales incorrectas");
							response.setUser(null);
						}
					} else {
						if (!userUpdate.getDevices().contains(request.getDevice())) {
							userUpdate.getDevices().add(request.getDevice());
							userRepository.save(userUpdate);
						}
						userUpdate.setPassword(null);
						List<User> lista = new ArrayList<User>();
						lista.add(userUpdate);
						response.setCode("0");
						response.setMessage("exito al en login");
						response.setUser(lista);
					}
				} else {
					response.setCode("-1");
					response.setMessage("Crendenciales incorrectas");
					response.setUser(null);
				}

			} else {
				response.setCode("-1");
				response.setMessage("Crendenciales incorrectas");
				response.setUser(null);
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un problema: " + e.getMessage());
			response.setUser(null);
		}

		return response;

	}

	public PasswordEncoder encoder() {
		try {
			return new BCryptPasswordEncoder();

		} catch (Exception e) {
			return null;
		}

	}

}
