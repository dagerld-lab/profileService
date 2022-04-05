package com.profileService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.profileService.entity.User;
import com.profileService.model.RequestFavourite;
import com.profileService.model.Response;
import com.profileService.model.ResponseUser;
import com.profileService.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = { "ProfileService" })
public class UserController {

	@Autowired
	UserService userService;

	// ---------------------------------------------------------------------
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registrar Usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }) })
	@PostMapping("/service/v1/userSave")
	public Response saveUser(@RequestBody User user) {

		return userService.saveUser(user);
	}

	// ---------------------------------------------------------------------
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualizar Usuario", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }) })
	@PostMapping("/service/v1/updateUser")
	public ResponseUser updateUser(@RequestBody User user) {

		return userService.updateUser(user);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualizar Preferencias", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }) })
	@PostMapping("/service/v1/updatePreferences")
	public ResponseUser updatePreferences(@RequestBody User user) {
		return userService.updatePreferences(user);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualizar Preferencias", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }) })
	@PostMapping("/service/v1/updateFavourite")
	public ResponseUser updateFavourite(@RequestBody RequestFavourite requestFavourite) {
		return userService.updateFavourite(requestFavourite);
	}

	// ---------------------------------------------------------------------
	@GetMapping("/service/v1/findUserEmail/{email}")
	public ResponseUser findUserEmail(@PathVariable String email) {

		return userService.findUser(email);
	}

	// ---------------------------------------------------------------------
	@PostMapping("/service/v1/deleteUser/{email}")
	public Response deleteUser(@PathVariable String email) {

		return userService.deleteUser(email);
	}

	@PostMapping("/service/v1/activeUser/{email}")
	public Response activeUser(@PathVariable String email) {

		return userService.activeUser(email);
	}

	@GetMapping("/service/v1/findAllUsers")
	public ResponseUser findAllUsers() {

		return userService.findAllUser();
	}

}
