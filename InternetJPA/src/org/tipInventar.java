package org;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.OneToMany;

@Entity
public class tipInventar {
	// Atribute private
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer tipId;
	
	private String nume;
	
	private String descriereCategorie;
	
	@OneToMany(mappedBy = "tipInventar")
	private List<Inventar> obiecteInventar;

	//Constructor
	public tipInventar(Integer tipId, String nume, String descriereCategorie) {
		super();
		this.tipId=tipId;
		this.nume = nume;
		this.descriereCategorie = descriereCategorie;
	}
	public tipInventar() {
		super();
	}
	//GetterSetter
	public Integer getTipId() {
		return tipId;
	}
	public void setTipId(Integer tipId) {
		this.tipId = tipId;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getDescriereCategorie() {
		return descriereCategorie;
	}
	public void setDescriereCategorie(String descriereCategorie) {
		this.descriereCategorie = descriereCategorie;
	}
	public List<Inventar> getObiecteInventar() {
		return obiecteInventar;
	}

	
	//Criteriu de egalitate
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriereCategorie == null) ? 0 : descriereCategorie.hashCode());
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		result = prime * result + ((obiecteInventar == null) ? 0 : obiecteInventar.hashCode());
		result = prime * result + ((tipId == null) ? 0 : tipId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		tipInventar other = (tipInventar) obj;
		if (descriereCategorie == null) {
			if (other.descriereCategorie != null)
				return false;
		} else if (!descriereCategorie.equals(other.descriereCategorie))
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (obiecteInventar == null) {
			if (other.obiecteInventar != null)
				return false;
		} else if (!obiecteInventar.equals(other.obiecteInventar))
			return false;
		if (tipId == null) {
			if (other.tipId != null)
				return false;
		} else if (!tipId.equals(other.tipId))
			return false;
		return true;
	}

	
	//Operatii Specifice
	
	@Override
	public String toString() {
		return "tipInventar [Categoria de inventar cu id-ul " + tipId + " este " + nume + " si reprezinta " + descriereCategorie + "]";
	}
	
	
}
