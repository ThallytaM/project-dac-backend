package br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer>{

	Optional<SystemRole> findByName(String name);
}
