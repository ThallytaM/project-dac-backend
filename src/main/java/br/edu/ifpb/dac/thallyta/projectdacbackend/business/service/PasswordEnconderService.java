package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

public interface PasswordEnconderService extends PasswordEncoder {
	
	void encryptPassword(User user);

}
