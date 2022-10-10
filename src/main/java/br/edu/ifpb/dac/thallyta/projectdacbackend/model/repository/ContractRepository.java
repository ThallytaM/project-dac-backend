package br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract,Integer>  {

}
