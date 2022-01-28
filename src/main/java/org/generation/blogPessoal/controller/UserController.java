package org.generation.blogPessoal.controller;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.model.dtos.UserCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UserLoginDTO;
import org.generation.blogPessoal.model.dtos.UserRegisterDTO;
import org.generation.blogPessoal.repository.UserRepository;
import org.generation.blogPessoal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

	private @Autowired UserRepository repository;
	private @Autowired UserService service;
	
	@PostMapping("/save")
	public ResponseEntity<UserModel> save(@Valid @RequestBody UserRegisterDTO newUser){
		return service.registerUser(newUser);
	}
	
	@PutMapping("/logar")
	public ResponseEntity<UserCredentialsDTO> credentials(@Valid @RequestBody UserLoginDTO user){
		return service.getCredentials(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> findById(@PathVariable(value = "id") long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe"));
	}
	
	@GetMapping
	public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String auth){
		return ResponseEntity.status(200).body(repository.findByToken(auth.replaceAll("Basic ", "")));
	}
}
