package com.profileService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.profileService.model.Response;
import com.profileService.model.ResponseUser;
import com.profileService.model.loginRequest;
import com.profileService.service.LoginService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "LoginService" })
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping("/service/v1/login")
	public ResponseUser Login(@RequestBody loginRequest request) {

		

		return loginService.Login(request);
	}

	@PostMapping("/service/v1/resetPassword")
	public Response resetPassword(@RequestBody loginRequest email) {

		Response resp = new Response();

		return resp;
	}

	@PostMapping("/service/v1/recoverPassword")
	public Response recoverPassword(@RequestBody String email) {

		Response resp = new Response();

		return resp;
	}

}
