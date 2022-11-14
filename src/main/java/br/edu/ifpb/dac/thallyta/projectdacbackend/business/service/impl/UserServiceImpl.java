package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.UserService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.SystemRole;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SystemRoleServiceImpl systemRoleService;
	
	@Autowired
	private PasswordEnconderService passwordEnconderService;
	
	public User save(User user) {
		if(user.getId() != null) {
			throw new IllegalStateException("User is already in the database. Maybe you can try update it.");
		}
		
		passwordEnconderService.encryptPassword(user);
		
		List<SystemRole> roles = new ArrayList<>();
		roles.add(systemRoleService.findDefault());
		user.setRoles(roles);
		return userRepository.save(user);
	}
	
	public User update(Integer id,User user) {
		User userUp = findById(id);
		userUp.setName(user.getName());
		userUp.setEmail(user.getEmail());
		userUp.setPassword(user.getPassword());
		
		return userRepository.save(user);
	}
	
	public void delete(Integer id) {
		User clientUp = findById(id);
		userRepository.deleteById(id);	
	}
	
	public User findById(Integer id) {
		if(id == null)
			throw new IllegalStateException();
		
		return userRepository.findById(id).get();
	}
	
	public List<User> find(User filter){
		
		Example<User> example = Example.of(filter,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return userRepository.findAll(example);
		
	}
	public User findByUsername(String username) {
		if(username == null) {
			throw new IllegalStateException("Username cannot be null");
		}
		
		Optional<User> optional = userRepository.findByUsername(username); 
		
		return optional.isPresent() ? optional.get() : null;
		
//		User user = userRepository.findByUsername(username).get(); 
//		
//		//verificar
//		return user ? user : null;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Could not find any user with username %s", username));
		}
		
		return user;
	
	}
	
	
}
