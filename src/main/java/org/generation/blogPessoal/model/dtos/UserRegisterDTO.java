package org.generation.blogPessoal.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterDTO {

	private @NotBlank @Size(min = 2, max = 50) String nome;
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 2, max = 40) String senha;
	private @NotBlank @Size(min = 2) String foto;
	private @NotBlank String tipo;

	public UserRegisterDTO() {

	}

	public UserRegisterDTO(String nome, String email, String senha, String foto, String tipo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
