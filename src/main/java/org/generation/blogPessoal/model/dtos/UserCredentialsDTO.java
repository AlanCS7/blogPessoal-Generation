package org.generation.blogPessoal.model.dtos;

public class UserCredentialsDTO {

	private Long idUser;
	private String nome;
	private String email;
	private String token;
	private String tokenBasic;
	private String foto;
	private String tipo;

	public UserCredentialsDTO() {

	}

	public UserCredentialsDTO(Long idUser, String nome, String email, String token, String tokenBasic, String foto,
			String tipo) {
		this.idUser = idUser;
		this.nome = nome;
		this.email = email;
		this.token = token;
		this.tokenBasic = tokenBasic;
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

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenBasic() {
		return tokenBasic;
	}

	public void setTokenBasic(String tokenBasic) {
		this.tokenBasic = tokenBasic;
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
