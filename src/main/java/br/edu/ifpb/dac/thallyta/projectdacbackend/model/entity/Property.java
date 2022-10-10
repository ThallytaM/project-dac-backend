package br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String address;
	private BigDecimal area;
	private BigDecimal rentValue;
	
	public Property() {
		
	}

	public Property(String address, BigDecimal area, BigDecimal rentValue) {
		this.address = address;
		this.area = area;
		this.rentValue = rentValue;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getRentValue() {
		return rentValue;
	}

	public void setRentValue(BigDecimal rentValue) {
		this.rentValue = rentValue;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(address, area, id, rentValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		return Objects.equals(address, other.address) && Objects.equals(area, other.area)
				&& Objects.equals(id, other.id) && Objects.equals(rentValue, other.rentValue);
	}

	@Override
	public String toString() {
		return "\nID do Imóvel: " + id+
				"\nAddress: " + address+
				"\nÁrea em m²: " + area+
				"\nRent Value: R$ " + rentValue;
	}
}
