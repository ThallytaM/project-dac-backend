package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;

public interface UserService extends UserDetailsService{

	
	public User save(User user);
	
	public User update(Integer id,User user);
	
	public void delete(Integer id);
	
	public User findById(Integer id);
	
	public User findByUsername(String username);
	
	public List<User> find(User filter);
	
	
}
