package br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.control;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ClientService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ConverterService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ValidationService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ClientDTO;


@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ConverterService converterService; 
	
	@Autowired
	private ValidationService validationService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody ClientDTO dto) {
		try {
			Client entity = converterService.dtoToClient(dto);
			validationService.validationAge(dto);
			entity = clientService.save(entity);
			dto = converterService.clientToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id")Integer id, @RequestBody ClientDTO  dto){	
		try {
			dto.setId(id);
			
			Integer clientId = dto.getId();
			Optional<Client> c = clientService.findById(clientId);
			
			if(c==null) {
				throw new IllegalStateException();
			}
			
			Client entity = converterService.dtoToClient(dto);
			entity = clientService.update(id, entity);
			dto = converterService.clientToDto(entity);
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteId(@PathVariable("id") Integer id) {
		try {
			clientService.deleteId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		}		
	}
	
	@GetMapping
	public ResponseEntity find(
			@RequestParam(value = "id",required = false) Integer id,
			@RequestParam(value = "name",required = false)String name,
			@RequestParam(value = "cpf",required = false)String cpf) {
		
		try {
			Client filter = new Client();
			filter.setId(id);
			filter.setName(name);
			filter.setCpf(cpf);
		
			
			List<Client> clients = clientService.find(filter);
			List<ClientDTO> dtos = converterService.clientToDto(clients);
			
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		}		
		}		
	
}
