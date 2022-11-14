package br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

public class UserDTO {
	
	private Integer id;
	private String name;
	private String username;
	private String email;
	private String password;
	
	private String[] roles;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(User user) {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	
	

}
