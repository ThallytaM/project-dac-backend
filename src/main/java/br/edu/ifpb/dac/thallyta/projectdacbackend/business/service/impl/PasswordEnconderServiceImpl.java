package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

@Service
public class PasswordEnconderServiceImpl extends BCryptPasswordEncoder implements PasswordEnconderService {

	@Override
	public void encryptPassword(User user) {
		if(user.getPassword() != null) {
			String encryptedPassword = encode(user.getPassword());
			user.setPassword(encryptedPassword);
		}
	}


}
