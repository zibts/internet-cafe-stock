package org;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Statii {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer statieId;
	
	@ElementCollection
	private List<Periferice> perifericeStatie;
	
	@ElementCollection
	private List<Mobilier> mobilierStatie;
	
	@ManyToOne(cascade = ALL)
	@JoinColumn(name="calculatorId")
	private calculator calculatorStatieId;

	public Statii(Integer statieId, List<Periferice> perifericeStatie, List<Mobilier> mobilierStatie,
			calculator calculatorStatieId) {
		super();
		this.statieId = statieId;
		this.perifericeStatie = perifericeStatie;
		this.mobilierStatie = mobilierStatie;
		this.calculatorStatieId = calculatorStatieId;
	}

	public Statii() {
		super();
	}

	public Integer getStatieId() {
		return statieId;
	}

	public void setStatieId(Integer statieId) {
		this.statieId = statieId;
	}

	public List<Periferice> getPerifericeStatie() {
		return perifericeStatie;
	}

	public void setPerifericeStatie(List<Periferice> perifericeStatie) {
		this.perifericeStatie = perifericeStatie;
	}

	public List<Mobilier> getMobilierStatie() {
		return mobilierStatie;
	}

	public void setMobilierStatie(List<Mobilier> mobilierStatie) {
		this.mobilierStatie = mobilierStatie;
	}

	public calculator getCalculatorStatieId() {
		return calculatorStatieId;
	}

	public void setCalculatorStatieId(calculator calculatorStatieId) {
		this.calculatorStatieId = calculatorStatieId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calculatorStatieId == null) ? 0 : calculatorStatieId.hashCode());
		result = prime * result + ((mobilierStatie == null) ? 0 : mobilierStatie.hashCode());
		result = prime * result + ((perifericeStatie == null) ? 0 : perifericeStatie.hashCode());
		result = prime * result + ((statieId == null) ? 0 : statieId.hashCode());
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
		Statii other = (Statii) obj;
		if (calculatorStatieId == null) {
			if (other.calculatorStatieId != null)
				return false;
		} else if (!calculatorStatieId.equals(other.calculatorStatieId))
			return false;
		if (mobilierStatie == null) {
			if (other.mobilierStatie != null)
				return false;
		} else if (!mobilierStatie.equals(other.mobilierStatie))
			return false;
		if (perifericeStatie == null) {
			if (other.perifericeStatie != null)
				return false;
		} else if (!perifericeStatie.equals(other.perifericeStatie))
			return false;
		if (statieId == null) {
			if (other.statieId != null)
				return false;
		} else if (!statieId.equals(other.statieId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Statii [statieId=" + statieId + ", perifericeStatie=" + perifericeStatie + ", mobilierStatie="
				+ mobilierStatie + ", calculatorStatieId=" + calculatorStatieId + "]";
	}
	
	

}
