package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ClientDTO;


@Service
public class ValidationService {
	
	@Autowired
	private ClientService clientService;
	
	public void validationAge(ClientDTO dto) throws Exception {
		Objects.requireNonNull(dto, "Client null");
		if(dto.getAge() < 18)
			throw new Exception("não");
	}
}
