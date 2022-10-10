package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Contract;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Property;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ClientDTO;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ContractDTO;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.PropertyDTO;


@Service
public class ConverterService {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private PropertyService propertyService;
	
	public List<ClientDTO> clientToDto(List<Client> clients){
		List<ClientDTO> clientDto = new ArrayList<>();
		
		for (Client client: clients) {
			ClientDTO dto = clientToDto(client);
			clientDto.add(dto);
		}
		return clientDto;	
	}
	
	public Client dtoToClient(ClientDTO dto) {
		Client client = new Client();
		
		client.setId(dto.getId());
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setTelephone(dto.getTelephone());
		
		return client;
		
	}
	
	public ClientDTO clientToDto(Client client) {
		
		ClientDTO dto = new ClientDTO(client);
		
		dto.setId(client.getId());
		dto.setName(client.getName());
		dto.setCpf(client.getCpf());
		dto.setTelephone(client.getTelephone());
		
		return dto;
		
	}
	
	public List<PropertyDTO> propertyToDto(List<Property> properties){
		List<PropertyDTO> propertyDto = new ArrayList<>();
		
		for (Property property: properties) {
			PropertyDTO dto = propertyToDto(property);
			propertyDto.add(dto);
		}
		return propertyDto;	
	}
	
	public Property dtoToProperty(PropertyDTO dto) {
		Property property = new Property();
		
		property.setId(dto.getId());
		property.setAddress(dto.getAddress());
		property.setArea(dto.getArea());
		property.setRentValue(dto.getRentValue());		
		return property;
		
	}
	
	public PropertyDTO propertyToDto(Property property) {
		
		PropertyDTO dto = new PropertyDTO(property);
		
		dto.setId(property.getId());
		dto.setAddress(property.getAddress());
		dto.setArea(property.getArea());
		dto.setRentValue(property.getRentValue());		
		return dto;
		
	}
	
	public List<ContractDTO> contractToDto(List<Contract> contracts){
		List<ContractDTO> contractDto = new ArrayList<>();
		
		for (Contract contract: contracts) {
			ContractDTO dto = contractToDto(contract);
			contractDto.add(dto);
		}
		return contractDto;	
	}
	
	public Contract dtoToContract(ContractDTO dto) {
		
		Contract contract = new Contract();
		
		contract.setId(dto.getId());
		contract.setContractDate(dto.getContractDate());
		if(dto.getClientId()!= null) {
			Optional<Client> client = clientService.findById(Integer.parseInt(dto.getClientId()));
			contract.setClient(client.get());
		}
		if(dto.getPropertyId() != null) {
			Property property = propertyService.findById(Integer.parseInt(dto.getPropertyId()));
			contract.setProperty(property);
		}
		return contract;
		
	}
	
	public ContractDTO contractToDto(Contract contract) {
		
		ContractDTO dto = new ContractDTO(contract);
		
		dto.setId(contract.getId());
		dto.setContractDate(contract.getContractDate());	
		if(contract.getClient() != null)
			dto.setClientId(Integer.toString(contract.getClient().getId()));
		if (contract.getProperty() != null)
			dto.setPropertyId(Integer.toString(contract.getProperty().getId()));
		return dto;
		
	}
	
	

	
	

}