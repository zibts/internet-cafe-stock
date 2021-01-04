package org;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.JoinFetch;

@Entity
public class Statii {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer statieId;
	
	
	@ManyToOne
	@JoinColumn(name="salaId")
	private salaCalculatoare salaStatieAmplasataId;
	
	
	@ManyToOne
	@JoinColumn(name="calculatorId")
	private calculator calculatorStatieId;
	
	@ManyToOne
	@JoinColumn(name="keyboardId")
	private perifericKeyboard keyboardStatieId;
	
	@ManyToOne
	@JoinColumn(name="mouseId")
	private perifericMouse mouseStatieId;
	
	@ManyToOne
	@JoinColumn(name="displayId")
	private perifericDisplay displayStatieId;
	
	@ManyToOne
	@JoinColumn(name="castiId")
	private perifericCasti castiStatieId;
	
	@ManyToOne
	@JoinColumn(name="birouId")
	private Mobilier birouStatie;
	
	@ManyToMany
	@JoinTable(name = "StatiiCalculatoare", joinColumns = @JoinColumn(name = "statieID", referencedColumnName = "statieId"), inverseJoinColumns = @JoinColumn(name = "obiectId", referencedColumnName = "obiectId"))
	private List<Inventar> inventarInStatie;

	public Statii(Integer statieId, salaCalculatoare salaStatieAmplasataId, calculator calculatorStatieId,
			perifericKeyboard keyboardStatieId, perifericMouse mouseStatieId, perifericDisplay displayStatieId,
			perifericCasti castiStatieId, Mobilier birouStatie) {
		super();
		this.statieId = statieId;
		this.salaStatieAmplasataId = salaStatieAmplasataId;
		this.calculatorStatieId = calculatorStatieId;
		this.keyboardStatieId = keyboardStatieId;
		this.mouseStatieId = mouseStatieId;
		this.displayStatieId = displayStatieId;
		this.castiStatieId = castiStatieId;
		this.birouStatie = birouStatie;
		this.inventarInStatie=this.createInventarInStatie();
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

	public salaCalculatoare getSalaStatieAmplasataId() {
		return salaStatieAmplasataId;
	}

	public void setSalaStatieAmplasataId(salaCalculatoare salaStatieAmplasataId) {
		this.salaStatieAmplasataId = salaStatieAmplasataId;
	}

	public calculator getCalculatorStatieId() {
		return calculatorStatieId;
	}

	public void setCalculatorStatieId(calculator calculatorStatieId) {
		this.calculatorStatieId = calculatorStatieId;
	}

	public perifericKeyboard getKeyboardStatieId() {
		return keyboardStatieId;
	}

	public void setKeyboardStatieId(perifericKeyboard keyboardStatieId) {
		this.keyboardStatieId = keyboardStatieId;
	}

	public perifericMouse getMouseStatieId() {
		return mouseStatieId;
	}

	public void setMouseStatieId(perifericMouse mouseStatieId) {
		this.mouseStatieId = mouseStatieId;
	}

	public perifericDisplay getDisplayStatieId() {
		return displayStatieId;
	}

	public void setDisplayStatieId(perifericDisplay displayStatieId) {
		this.displayStatieId = displayStatieId;
	}

	public perifericCasti getCastiStatieId() {
		return castiStatieId;
	}

	public void setCastiStatieId(perifericCasti castiStatieId) {
		this.castiStatieId = castiStatieId;
	}

	public Mobilier getBirouStatie() {
		return birouStatie;
	}

	public void setBirouStatie(Mobilier birouStatie) {
		this.birouStatie = birouStatie;
	}
	public List<Inventar> createInventarInStatie() {
		List<Inventar> intermed = new ArrayList<Inventar>();
		intermed.add(this.calculatorStatieId);
		intermed.add(this.castiStatieId);
		intermed.add(this.displayStatieId);
		intermed.add(this.keyboardStatieId);
		intermed.add(this.birouStatie);
		intermed.add(this.mouseStatieId);
		return intermed;
	}
	

	public List<Inventar> getInventarInStatie() {
		return inventarInStatie;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calculatorStatieId == null) ? 0 : calculatorStatieId.hashCode());
		result = prime * result + ((castiStatieId == null) ? 0 : castiStatieId.hashCode());
		result = prime * result + ((displayStatieId == null) ? 0 : displayStatieId.hashCode());
		result = prime * result + ((keyboardStatieId == null) ? 0 : keyboardStatieId.hashCode());
		result = prime * result + ((birouStatie == null) ? 0 : birouStatie.hashCode());
		result = prime * result + ((mouseStatieId == null) ? 0 : mouseStatieId.hashCode());
		result = prime * result + ((salaStatieAmplasataId == null) ? 0 : salaStatieAmplasataId.hashCode());
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
		if (castiStatieId == null) {
			if (other.castiStatieId != null)
				return false;
		} else if (!castiStatieId.equals(other.castiStatieId))
			return false;
		if (displayStatieId == null) {
			if (other.displayStatieId != null)
				return false;
		} else if (!displayStatieId.equals(other.displayStatieId))
			return false;
		if (keyboardStatieId == null) {
			if (other.keyboardStatieId != null)
				return false;
		} else if (!keyboardStatieId.equals(other.keyboardStatieId))
			return false;
		if (birouStatie == null) {
			if (other.birouStatie != null)
				return false;
		} else if (!birouStatie.equals(other.birouStatie))
			return false;
		if (mouseStatieId == null) {
			if (other.mouseStatieId != null)
				return false;
		} else if (!mouseStatieId.equals(other.mouseStatieId))
			return false;
		if (salaStatieAmplasataId == null) {
			if (other.salaStatieAmplasataId != null)
				return false;
		} else if (!salaStatieAmplasataId.equals(other.salaStatieAmplasataId))
			return false;
		if (statieId == null) {
			if (other.statieId != null)
				return false;
		} else if (!statieId.equals(other.statieId))
			return false;
		return true;
	}
	
	
	

}
