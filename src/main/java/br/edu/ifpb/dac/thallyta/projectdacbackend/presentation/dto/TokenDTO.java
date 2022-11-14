package br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto;

public class TokenDTO {
	
	private String token;
	private UserDTO user;
	
	public TokenDTO() {
	}
	
	public TokenDTO(String token2, UserDTO userDTO) {
		this.token = token2;
		this.user = userDTO;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	

}
