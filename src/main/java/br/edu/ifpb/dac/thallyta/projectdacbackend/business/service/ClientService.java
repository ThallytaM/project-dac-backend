package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Contract;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.ClientRepository;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) { 		
		return clientRepository.save(client);
	}
	
	public Client update(Client client) {
		return clientRepository.save(client);
	}
	
	public void deleteId(Integer id) {
		clientRepository.deleteById(id);	
	}
	
	public Optional<Client> findById(Integer id) {
		if(id == null)
			throw new IllegalStateException("Null id client");
		
		return clientRepository.findById(id);
	}
	
	public List<Client> find(Client filter){
		
		Example example = Example.of(filter,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return clientRepository.findAll(example);
		
	}
	
	
	

}
