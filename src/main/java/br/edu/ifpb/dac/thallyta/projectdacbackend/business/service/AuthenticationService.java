package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

public interface AuthenticationService {
	
	public String login(String email, String password);
	
	public User getLoggedUser();

}
