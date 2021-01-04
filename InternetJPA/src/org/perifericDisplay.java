package org;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class perifericDisplay extends Periferice {

	private double diagonalaDisplayCm;
	private Integer refreshRateHz;
	private Boolean displayCurved;
	
	@OneToMany(mappedBy = "displayStatieId", cascade = ALL, orphanRemoval = true)
	private List<Statii> statiiCuDisplay = new ArrayList<Statii>();
	
	public perifericDisplay(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			 Integer stocTotal, org.tipInventar tipInventar, String perifericTip,
			String perifericPort, double diagonalaDisplayCm, Integer refreshRateHz, Boolean displayCurved) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocTotal, tipInventar, perifericTip,
				perifericPort);
		this.diagonalaDisplayCm = diagonalaDisplayCm;
		this.refreshRateHz = refreshRateHz;
		this.displayCurved = displayCurved;
	}
	public perifericDisplay() {
		super();
	}
	public double getDiagonalaDisplayCm() {
		return diagonalaDisplayCm;
	}
	public void setDiagonalaDisplayCm(double diagonalaDisplayCm) {
		this.diagonalaDisplayCm = diagonalaDisplayCm;
	}
	public Integer getRefreshRateHz() {
		return refreshRateHz;
	}
	public void setRefreshRateHz(Integer refreshRateHz) {
		this.refreshRateHz = refreshRateHz;
	}
	public Boolean getDisplayCurved() {
		return displayCurved;
	}
	public void setDisplayCurved(Boolean displayCurved) {
		this.displayCurved = displayCurved;
	}
	public List<Statii> getStatiiCuDisplay() {
		return statiiCuDisplay;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(diagonalaDisplayCm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((displayCurved == null) ? 0 : displayCurved.hashCode());
		result = prime * result + ((refreshRateHz == null) ? 0 : refreshRateHz.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		perifericDisplay other = (perifericDisplay) obj;
		if (Double.doubleToLongBits(diagonalaDisplayCm) != Double.doubleToLongBits(other.diagonalaDisplayCm))
			return false;
		if (displayCurved == null) {
			if (other.displayCurved != null)
				return false;
		} else if (!displayCurved.equals(other.displayCurved))
			return false;
		if (refreshRateHz == null) {
			if (other.refreshRateHz != null)
				return false;
		} else if (!refreshRateHz.equals(other.refreshRateHz))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+" "+this.getPerifericTip()+" "+this.getPerifericPort()+" " +"perifericDisplay [diagonalaDisplayCm=" + diagonalaDisplayCm + ", refreshRateHz=" + refreshRateHz
				+ ", displayCurved=" + displayCurved + "]";
	}

	public void seteazaDisplayLaStatie(Statii s) {
		s.setDisplayStatieId(this);
	}
}
