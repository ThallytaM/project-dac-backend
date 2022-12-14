package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.ClientRepository;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ClientDTO;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) { 		
		return clientRepository.save(client);
	}
	
	public Client update(Integer id,Client client) {
		Client clientUp = findById(id).get();
		clientUp.setName(client.getName());
		clientUp.setCpf(client.getCpf());
		clientUp.setTelephone(client.getTelephone());
		return clientRepository.save(client);
	}
	
	public void deleteId(Integer id) {
		Client clientUp = findById(id).get();
		clientRepository.deleteById(id);	
	}
	
	public Optional<Client> findById(Integer id) {
		if(id == null)
			throw new IllegalStateException();
		
		return clientRepository.findById(id);
	}
	
	public List<Client> find(Client filter){
		
		Example<Client> example = Example.of(filter,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return clientRepository.findAll(example);
		
	}
	
	
	

}
