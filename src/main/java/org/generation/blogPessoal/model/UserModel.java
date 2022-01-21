package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class UserModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;

	private String token;

	private String nome;

	private String email;

	private String senha;

	private String foto;

	private String tipo;

	@OneToMany(mappedBy = "userModel", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("userModel")
	private List<Postagem> postagens;

	public UserModel() {

	}

	public UserModel(String token, String nome, String email, String senha, String foto, String tipo) {
		this.token = token;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

	public String getToken() {
		return token;
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

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

}
