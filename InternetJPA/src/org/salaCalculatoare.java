package org;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;

@Entity
public class salaCalculatoare {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer salaId;
	private String denumireSala;
	private Integer nrStatiiPosibile;
	private Integer nrStatiiAmplasate;
	
	@OneToMany(mappedBy = "salaStatieAmplasataId", cascade = ALL, orphanRemoval = true)
	private List<Statii> statiiInSala = new ArrayList<Statii>();
	
//	
	public salaCalculatoare(Integer salaId, String denumireSala, Integer nrStatiiPosibile) {
		super();
		this.salaId = salaId;
		this.denumireSala = denumireSala;
		this.nrStatiiPosibile = nrStatiiPosibile;
	}
	
	public salaCalculatoare() {
		super();
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public String getDenumireSala() {
		return denumireSala;
	}

	public void setDenumireSala(String denumireSala) {
		this.denumireSala = denumireSala;
	}

	public Integer getNrStatiiPosibile() {
		return nrStatiiPosibile;
	}

	public void setNrStatiiPosibile(Integer nrStatiiPosibile) {
		this.nrStatiiPosibile = nrStatiiPosibile;
	}

	public Integer getNrStatiiAmplasate() {
		if (this.getStatiiInSala()==null) {
			return 0;
		}
		else {
		return this.getStatiiInSala().size();
		}
	}

	public void setNrStatiiAmplasate(Integer nrStatiiAmplasate) {
		this.nrStatiiAmplasate = nrStatiiAmplasate;
	}

	public List<Statii> getStatiiInSala() {
		return statiiInSala;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denumireSala == null) ? 0 : denumireSala.hashCode());
		result = prime * result + ((nrStatiiAmplasate == null) ? 0 : nrStatiiAmplasate.hashCode());
		result = prime * result + ((nrStatiiPosibile == null) ? 0 : nrStatiiPosibile.hashCode());
		result = prime * result + ((salaId == null) ? 0 : salaId.hashCode());
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
		salaCalculatoare other = (salaCalculatoare) obj;
		if (denumireSala == null) {
			if (other.denumireSala != null)
				return false;
		} else if (!denumireSala.equals(other.denumireSala))
			return false;
		if (nrStatiiAmplasate == null) {
			if (other.nrStatiiAmplasate != null)
				return false;
		} else if (!nrStatiiAmplasate.equals(other.nrStatiiAmplasate))
			return false;
		if (nrStatiiPosibile == null) {
			if (other.nrStatiiPosibile != null)
				return false;
		} else if (!nrStatiiPosibile.equals(other.nrStatiiPosibile))
			return false;
		if (salaId == null) {
			if (other.salaId != null)
				return false;
		} else if (!salaId.equals(other.salaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "salaCalculatoare [Sala de calculatoare cu Id-ul " + salaId + ",are numele " + denumireSala + ", are capacitatea de "
				+ nrStatiiPosibile + " calculatoare, acum fiind:" + nrStatiiAmplasate + "]";
	}
	
	
	
	
		
}
