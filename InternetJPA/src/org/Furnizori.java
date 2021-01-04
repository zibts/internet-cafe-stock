package org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;


@Entity
public class Furnizori {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer furnizorId;

	private String furnizorNume;
	
	private String furnizorAdresa;
	
	private String furnizorOras;
	
	private Boolean furnizorLivrare;
	
	@OneToMany(mappedBy = "furnizor", cascade = ALL, orphanRemoval = true)
	private List<furnizoriOferta> furnizoriOfertanti=new ArrayList<furnizoriOferta>();

	//Constructor
	public Furnizori(Integer furnizorId, String furnizorNume, String furnizorAdresa, String furnizorOras,
			Boolean furnizorLivrare) {
		super();
		this.furnizorId = furnizorId;
		this.furnizorNume = furnizorNume;
		this.furnizorAdresa = furnizorAdresa;
		this.furnizorOras = furnizorOras;
		this.furnizorLivrare = furnizorLivrare;
	}
	
	public Furnizori() {
		super();
	}

	public Integer getFurnizorId() {
		return furnizorId;
	}

	public void setFurnizorId(Integer furnizorId) {
		this.furnizorId = furnizorId;
	}

	public String getFurnizorNume() {
		return furnizorNume;
	}

	public void setFurnizorNume(String furnizorNume) {
		this.furnizorNume = furnizorNume;
	}

	public String getFurnizorAdresa() {
		return furnizorAdresa;
	}

	public void setFurnizorAdresa(String furnizorAdresa) {
		this.furnizorAdresa = furnizorAdresa;
	}

	public String getFurnizorOras() {
		return furnizorOras;
	}

	public void setFurnizorOras(String furnizorOras) {
		this.furnizorOras = furnizorOras;
	}

	public Boolean getFurnizorLivrare() {
		return furnizorLivrare;
	}

	public void setFurnizorLivrare(Boolean furnizorLivrare) {
		this.furnizorLivrare = furnizorLivrare;
	}

	public List<furnizoriOferta> getFurnizoriOfertanti() {
		return furnizoriOfertanti;
	}
	
	//Criteriu de egalitate
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((furnizorAdresa == null) ? 0 : furnizorAdresa.hashCode());
		result = prime * result + ((furnizorId == null) ? 0 : furnizorId.hashCode());
		result = prime * result + ((furnizorLivrare == null) ? 0 : furnizorLivrare.hashCode());
		result = prime * result + ((furnizorNume == null) ? 0 : furnizorNume.hashCode());
		result = prime * result + ((furnizorOras == null) ? 0 : furnizorOras.hashCode());
		result = prime * result + ((furnizoriOfertanti == null) ? 0 : furnizoriOfertanti.hashCode());
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
		Furnizori other = (Furnizori) obj;
		if (furnizorAdresa == null) {
			if (other.furnizorAdresa != null)
				return false;
		} else if (!furnizorAdresa.equals(other.furnizorAdresa))
			return false;
		if (furnizorId == null) {
			if (other.furnizorId != null)
				return false;
		} else if (!furnizorId.equals(other.furnizorId))
			return false;
		if (furnizorLivrare == null) {
			if (other.furnizorLivrare != null)
				return false;
		} else if (!furnizorLivrare.equals(other.furnizorLivrare))
			return false;
		if (furnizorNume == null) {
			if (other.furnizorNume != null)
				return false;
		} else if (!furnizorNume.equals(other.furnizorNume))
			return false;
		if (furnizorOras == null) {
			if (other.furnizorOras != null)
				return false;
		} else if (!furnizorOras.equals(other.furnizorOras))
			return false;
		if (furnizoriOfertanti == null) {
			if (other.furnizoriOfertanti != null)
				return false;
		} else if (!furnizoriOfertanti.equals(other.furnizoriOfertanti))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Furnizori [Furnizorul cu id-ul " + furnizorId + ", are denumirea " + furnizorNume + ", adresa "
				+ furnizorAdresa + "," + furnizorOras + ".Dispune de livrare :" + furnizorLivrare+ "]";
	}


	
	
	
	
	

	
	
	

}