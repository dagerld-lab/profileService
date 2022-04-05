package com.profileService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import com.profileService.entity.User;
import com.profileService.enums.AuthType;
import com.profileService.model.RequestFavourite;
import com.profileService.model.Response;
import com.profileService.model.ResponseUser;
import com.profileService.repository.UserRepository;

@Service
@Log4j2
public class UserService {

	@Autowired
	UserRepository userRepository;

	/// METODO CREAR USUARIO
	public Response saveUser(User user) {
		log.info(user);
		Response response = new Response();
		User userUpdate = new User();
		userUpdate = userRepository.findByEmail(user.getEmail());
		try {

			if (userUpdate == null) {
				log.info("No existe el usuario");
				log.info(AuthType.EMAIL.name());
				log.info(user.getType());
				if (user.getType().equals(AuthType.EMAIL.name())) {
					user.setPassword(encoder().encode(user.getPassword()));
				}
				user.setStatus("A");
				userRepository.save(user);
				response.setCode("0");
				response.setMessage("El usuario " + user.getName() + " se creo con exito");
			} else {
				response.setCode("-1");
				response.setMessage("El usuario ya existe");
			}

		} catch (Exception e) {
			response.setCode("-1");
			response.setMessage("Ocurrio un error de excepcion: " + e);

		}

		return response;

	}

	// METODO ACTUALIZAR USUARIO
	public ResponseUser updateUser(User user) {

		ResponseUser response = new ResponseUser();
		User userUpdate = new User();
		userUpdate = userRepository.findByEmail(user.getEmail());

		try {
			if (userUpdate != null) {
				List<User> lista = new ArrayList<User>();
				userUpdate.setName(user.getName());
				userUpdate.setAge(user.getAge());
				userUpdate.setCountry(user.getCountry());
				userUpdate.setImageProfileBytes(user.getImageProfileBytes());
				userRepository.save(userUpdate);
				userUpdate.setPassword(null);
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

	// BUSCAR USUARIO POR CORREO
	public ResponseUser findUser(String email) {

		ResponseUser response = new ResponseUser();
		User user = new User();
		try {
			user = userRepository.findByEmail(email);
			if (user != null) {
				List<User> lista = new ArrayList<User>();
				response.setCode("0");
				response.setMessage("exito en la consulta de: " + email);
				user.setPassword(null);
				lista.add(user);
				response.setUser(lista);
			} else {
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " + e.getMessage());
		}
		return response;
	}

	// INACTIVAR USUARIO
	public Response deleteUser(String email) {

		Response response = new Response();
		User userDelete = new User();
		try {

			userDelete = userRepository.findByEmail(email);
			if (userDelete != null) {
				userDelete.setStatus("I");
				userRepository.save(userDelete);
				response.setCode("0");
				response.setMessage("Se elimino el usuario " + userDelete.getName());
			} else {
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " + e.getMessage());
		}
		return response;

	}

	public Response activeUser(String email) {

		Response response = new Response();
		User userDelete = new User();
		try {

			userDelete = userRepository.findByEmail(email);
			if (userDelete != null) {
				userDelete.setStatus("A");
				userRepository.save(userDelete);
				response.setCode("0");
				response.setMessage("Se activo el usuario " + userDelete.getName());
			} else {
				response.setCode("-1");
				response.setMessage("El usuario no existe");
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " + e.getMessage());
		}
		return response;

	}

	public ResponseUser findAllUser() {

		ResponseUser response = new ResponseUser();
		List<User> lista = new ArrayList<User>();
		try {
			lista = userRepository.findAll();
			if (lista != null) {

				response.setCode("0");
				response.setMessage("exito en la consulta de: ");
				response.setUser(lista);
			} else {
				response.setCode("-1");
				response.setMessage("No hay registros");
			}

		} catch (Exception e) {
			response.setCode("-2");
			response.setMessage("Ocurrio un error de exception: " + e.getMessage());
		}
		return response;
	}

	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public ResponseUser updatePreferences(User user) {

		ResponseUser response = new ResponseUser();
		User userUpdate = new User();
		userUpdate = userRepository.findByEmail(user.getEmail());

		try {
			if (userUpdate != null) {
				List<User> lista = new ArrayList<User>();
				userUpdate.setDistance(user.getDistance());
				userUpdate.setInterests(user.getInterests());
				userRepository.save(userUpdate);
				response.setCode("0");
				response.setMessage("Se actualizo con exito");
				userUpdate.setPassword(null);
				lista.add(userUpdate);
				response.setUser(lista);
			} else {
				response.setCode("-1");
				response.setMessage("No actualizo");
				response.setUser(null);
			}

		} catch (Exception e) {
			response.setCode("-1");
			response.setMessage("Ocurrio una exception de " + e.getMessage());
			response.setUser(null);
		}

		return response;
	}

	public ResponseUser updateFavourite(RequestFavourite requestFavourite) {
		ResponseUser response = new ResponseUser();
		try {

			User userUpdate = userRepository.findByEmail(requestFavourite.getEmail());
				List<String> favourites = userUpdate.getFavourites();
			if(favourites==null){
				favourites=new ArrayList<String>();
			}
			if (favourites.contains(requestFavourite.getPlaceId())) {
				favourites = favourites.stream().filter(x -> !x.equalsIgnoreCase(requestFavourite.getPlaceId()))
						.collect(Collectors.toList());
				userUpdate.setFavourites(favourites);
			} else {
				favourites.add(requestFavourite.getPlaceId());
				userUpdate.setFavourites(favourites);
			}
			userRepository.save(userUpdate);
			userUpdate.setPassword(null);
			response.setCode("0");
			response.setMessage("Se actualizo con exito");
			response.setUser(Arrays.asList(userUpdate));
		} catch (Exception e) {
			log.error("Error al actualizar los favoritos: " + e.getMessage());
			response.setCode("-1");
			response.setMessage("Ocurrio una exception de " + e.getMessage());
			response.setUser(null);
		}
		return response;
	}

}
