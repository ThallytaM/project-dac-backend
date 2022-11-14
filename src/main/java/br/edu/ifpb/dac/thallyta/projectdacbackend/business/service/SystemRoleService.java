package br.edu.ifpb.dac.thallyta.projectdacbackend.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.AvailableRoles;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.SystemRole;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.SystemRoleRepository;


public interface SystemRoleService {
	
	public enum AVAILABLE_ROLES{ADMIN,USER};
	
	public void createDefaultValues();
	
	public SystemRole findByName(String name);
	
	public SystemRole findDefault();
}
