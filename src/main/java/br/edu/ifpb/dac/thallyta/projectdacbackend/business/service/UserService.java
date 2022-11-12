package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) { 		
		return userRepository.save(user);
	}
	
	public User update(Integer id,User user) {
		User userUp = findById(id).get();
		userUp.setName(user.getName());
		userUp.setEmail(user.getEmail());
		userUp.setPassword(user.getPassword());
		
		return userRepository.save(user);
	}
	
	public void deleteId(Integer id) {
		User clientUp = findById(id).get();
		userRepository.deleteById(id);	
	}
	
	public Optional<User> findById(Integer id) {
		if(id == null)
			throw new IllegalStateException();
		
		return userRepository.findById(id);
	}
	
	public List<User> find(User filter){
		
		Example<User> example = Example.of(filter,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return userRepository.findAll(example);
		
	}
}
