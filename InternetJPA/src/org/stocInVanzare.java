package org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

@Entity
public class stocInVanzare {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer ofertaVanzareId;
	
	@ManyToOne(cascade = ALL)
	@JoinColumn(name="obiectId")
	private Inventar obiectId;
	
	private Float pretVanzare;

	public stocInVanzare(Integer ofertaVanzareId, Inventar obiectId, Float pretVanzare) {
		super();
		this.ofertaVanzareId = ofertaVanzareId;
		this.obiectId = obiectId;
		this.pretVanzare = pretVanzare;
	}

	public stocInVanzare() {
		super();
	}

	public Integer getOfertaVanzareId() {
		return ofertaVanzareId;
	}

	public void setOfertaVanzareId(Integer ofertaVanzareId) {
		this.ofertaVanzareId = ofertaVanzareId;
	}

	public Inventar getObiectId() {
		return obiectId;
	}

	public void setObiectId(Inventar obiectId) {
		this.obiectId = obiectId;
	}

	public Float getPretVanzare() {
		return pretVanzare;
	}

	public void setPretVanzare(Float pretVanzare) {
		this.pretVanzare = pretVanzare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obiectId == null) ? 0 : obiectId.hashCode());
		result = prime * result + ((ofertaVanzareId == null) ? 0 : ofertaVanzareId.hashCode());
		result = prime * result + ((pretVanzare == null) ? 0 : pretVanzare.hashCode());
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
		stocInVanzare other = (stocInVanzare) obj;
		if (obiectId == null) {
			if (other.obiectId != null)
				return false;
		} else if (!obiectId.equals(other.obiectId))
			return false;
		if (ofertaVanzareId == null) {
			if (other.ofertaVanzareId != null)
				return false;
		} else if (!ofertaVanzareId.equals(other.ofertaVanzareId))
			return false;
		if (pretVanzare == null) {
			if (other.pretVanzare != null)
				return false;
		} else if (!pretVanzare.equals(other.pretVanzare))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "stocInVanzare [ofertaVanzareId=" + ofertaVanzareId + ", obiectId=" + obiectId + ", pretVanzare="
				+ pretVanzare + "]";
	}
	
	

}
