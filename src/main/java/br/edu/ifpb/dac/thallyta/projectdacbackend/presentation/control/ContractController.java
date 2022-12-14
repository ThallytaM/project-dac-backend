package br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.control;

import java.util.Date;
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
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ContractService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ConverterService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.PropertyService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Contract;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Property;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ContractDTO;


@RestController
@RequestMapping("/api/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ConverterService converterService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody ContractDTO dto) {
		try {
			
			Contract entity = converterService.dtoToContract(dto);
			entity = contractService.save(entity);
			dto = converterService.contractToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id")Integer id, @RequestBody ContractDTO  dto){	
		try {
			dto.setId(id);
			Contract entity = converterService.dtoToContract(dto);
			entity = contractService.update(id,entity);
			dto = converterService.contractToDto(entity);
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteId(@PathVariable("id") Integer id) {
		try {
			contractService.deleteId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		
		}		
	}
	
	@GetMapping
	public ResponseEntity find(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "contractDate", required = false) Date contractDate)
//			@RequestParam(value = "clientId", required = false) String clientId,
//			@RequestParam(value = "propertyId", required = false) String propertyId)	
	{
		
			try {
				Contract filter = new Contract();
				filter.setId(id);	
				filter.setContractDate(contractDate);
					
//				Optional<Client> client = clientService.findById(Integer.parseInt(clientId));
//				if (client == null) {
//					throw new IllegalStateException("Cliente nulo");
//				}else {
//					filter.setClient(client.get());
//
//				}				
//				Property property = propertyService.findById(Integer.parseInt(propertyId));
//				if (property == null) {
//					throw new IllegalStateException("Propriedade nula");
//				}else {
//					filter.setProperty(property);	
//				}
					
			
			
			List<Contract> contracts = contractService.find(filter);
			List<ContractDTO> dtos = converterService.contractToDto(contracts);
			
			
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		}		
		}	

}
