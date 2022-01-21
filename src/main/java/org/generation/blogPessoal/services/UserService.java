package org.generation.blogPessoal.services;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.model.dtos.UserCredentialsDTO;
import org.generation.blogPessoal.model.dtos.UserLoginDTO;
import org.generation.blogPessoal.model.dtos.UserRegisterDTO;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	private @Autowired UserRepository repository;
	private UserCredentialsDTO credentials;
	private UserModel userModel;
	
	private static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	private static String generatorToken(String email, String password) {
		String tokenModel = email + ":" + password;
		byte[] tokenModelBase64 = Base64.encodeBase64(tokenModel.getBytes(Charset.forName("US-ASCII")));
		return new String(tokenModelBase64);
	}
	
	private static String generatorTokenBasic(String email, String password) {
		String tokenModel = email + ":" + password;
		byte[] tokenModelBase64 = Base64.encodeBase64(tokenModel.getBytes(Charset.forName("US-ASCII")));
		return "Basic " +  new String(tokenModelBase64);
	}
	
	public ResponseEntity<UserModel> registerUser(@Valid UserRegisterDTO newUser){
		Optional<UserModel> optional = repository.findByEmail(newUser.getEmail());
		
		if (optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!");
		} else {
			userModel = new UserModel(
					generatorToken(newUser.getEmail(), newUser.getSenha()),
					newUser.getNome(),
					newUser.getEmail(),
					encryptPassword(newUser.getSenha()),
					newUser.getFoto(),
					newUser.getTipo());
			
			return ResponseEntity.status(201).body(repository.save(userModel));
		}
	}
	
	public ResponseEntity<UserCredentialsDTO> getCredentials(@Valid UserLoginDTO userDTO){
		return repository.findByEmail(userDTO.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if(encoder.matches(userDTO.getSenha(), resp.getSenha())) {
				credentials = new UserCredentialsDTO(
						resp.getIdUser(),
						resp.getNome(),
						resp.getEmail(),
						resp.getToken(),
						resp.getFoto(),
						resp.getTipo(),
						generatorTokenBasic(userDTO.getEmail(), userDTO.getSenha()));
			
				return ResponseEntity.status(200).body(credentials);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta!");
			}
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email incorreto!"));
	}
}
