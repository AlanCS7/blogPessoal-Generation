package org.generation.blogPessoal.model.dtos;

public class UserCredentialsDTO {

	private Long idUser;
	private String email;
	private String token;
	private String tokenBasic;

	public UserCredentialsDTO() {

	}

	public UserCredentialsDTO(Long idUser, String email, String token, String tokenBasic) {
		this.idUser = idUser;
		this.email = email;
		this.token = token;
		this.tokenBasic = tokenBasic;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

}
