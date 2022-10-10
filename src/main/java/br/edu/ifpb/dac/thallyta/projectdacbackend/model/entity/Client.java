package br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String cpf; 
	private String telephone;
	
	//@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private List<Contract> contracts;

	
	public Client() {
	}
		
	public Client(String name, String cpf, String telephone ) {
		this.name = name;
		this.cpf = cpf;
		this.telephone = telephone;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
/*
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
*/
	@Override
	public String toString() {
		return "id = "+ id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id, name, telephone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(telephone, other.telephone);
	}

	
	
	
	
}
