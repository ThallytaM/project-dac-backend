package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.AuthenticationService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.SuapService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.TokenService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.UserService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Value("${app.logintype}")
	private String logintype;
	
	private String suapToken;
	
	
	
	private String localLogin(String username, String password) {	

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		User user = userService.findByUsername(username);
		
		return tokenService.generate(user);
	}
	
	public User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}

	@Override
	public String login(String username, String password) {
		return localLogin(username, password);
	}

}
