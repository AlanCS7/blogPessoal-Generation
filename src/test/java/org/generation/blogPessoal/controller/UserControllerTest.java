package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.model.dtos.UserCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UserLoginDTO;
import org.generation.blogPessoal.model.dtos.UserRegisterDTO;
import org.generation.blogPessoal.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.jupiter.api.Test;

class UserControllerTest {

	private @Autowired TestRestTemplate testRest;
	private @Autowired UserService services;
	
	@Test
	@Order(3)
	@DisplayName("Register new User!")
	void saveNewUserReturn201() {
		//Given
		HttpEntity<UserRegisterDTO> request = new HttpEntity<UserRegisterDTO>(
				new UserRegisterDTO("Alan Boaz","alan@email.com","134652"));
		
		//When
		ResponseEntity<UserModel> response =
				testRest.exchange("/usuarios", HttpMethod.POST, request, UserModel.class);
		
		//Then
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	@DisplayName("Get credentials User!")
	void getCredentialsUserReturn200() {
		//Given
		services.registerUser(new UserRegisterDTO("Murilo Boaz","murilo@email.com","134652"));
		HttpEntity<UserLoginDTO> request = new HttpEntity<UserLoginDTO>(
				new UserLoginDTO("murilo@email.com","134652"));
		
		//When
		ResponseEntity<UserCredentialsDTO> response =
				testRest.exchange("/usuarios/logar", HttpMethod.PUT, request, UserCredentialsDTO.class);
		
		//Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(1)
	@DisplayName("Get profile User!")
	void getProfileUserReturn200() {
		//Given
		services.registerUser(new UserRegisterDTO("Otaio Boaz","otavio@email.com","134652"));
		
		//When
		ResponseEntity<UserModel> response =
				testRest.withBasicAuth("otavio@email.com", "134652")
				.exchange("/usuarios", HttpMethod.GET, null, UserModel.class);
		
		//Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}


}
