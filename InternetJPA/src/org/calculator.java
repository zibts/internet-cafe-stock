package org;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class calculator extends Inventar {
	private String procesor;
	private Integer memorieRamMb;
	private String cardVideo;
	private String tipStocare;
	private double capacitateStogareGb;
	
	@OneToMany(mappedBy = "calculatorStatieId")
	private List<Statii> calculatoareInStatii;

	public calculator(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			Integer stocDisponibil, Integer stocTotal, org.tipInventar tipInventar, String procesor,
			Integer memorieRamMb, String cardVideo, String tipStocare, double capacitateStogareGb) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocDisponibil, stocTotal, tipInventar);
		this.procesor = procesor;
		this.memorieRamMb = memorieRamMb;
		this.cardVideo = cardVideo;
		this.tipStocare = tipStocare;
		this.capacitateStogareGb = capacitateStogareGb;
	}
	public calculator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProcesor() {
		return procesor;
	}

	public void setProcesor(String procesor) {
		this.procesor = procesor;
	}

	public Integer getMemorieRamMb() {
		return memorieRamMb;
	}

	public void setMemorieRamMb(Integer memorieRamMb) {
		this.memorieRamMb = memorieRamMb;
	}

	public String getCardVideo() {
		return cardVideo;
	}

	public void setCardVideo(String cardVideo) {
		this.cardVideo = cardVideo;
	}

	public String getTipStocare() {
		return tipStocare;
	}

	public void setTipStocare(String tipStocare) {
		this.tipStocare = tipStocare;
	}

	public double getCapacitateStogareGb() {
		return capacitateStogareGb;
	}

	public void setCapacitateStogareGb(double capacitateStogareGb) {
		this.capacitateStogareGb = capacitateStogareGb;
	}

	public List<Statii> getCalculatoareInStatii() {
		return calculatoareInStatii;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((calculatoareInStatii == null) ? 0 : calculatoareInStatii.hashCode());
		long temp;
		temp = Double.doubleToLongBits(capacitateStogareGb);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cardVideo == null) ? 0 : cardVideo.hashCode());
		result = prime * result + ((memorieRamMb == null) ? 0 : memorieRamMb.hashCode());
		result = prime * result + ((procesor == null) ? 0 : procesor.hashCode());
		result = prime * result + ((tipStocare == null) ? 0 : tipStocare.hashCode());
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
		calculator other = (calculator) obj;
		if (calculatoareInStatii == null) {
			if (other.calculatoareInStatii != null)
				return false;
		} else if (!calculatoareInStatii.equals(other.calculatoareInStatii))
			return false;
		if (Double.doubleToLongBits(capacitateStogareGb) != Double.doubleToLongBits(other.capacitateStogareGb))
			return false;
		if (cardVideo == null) {
			if (other.cardVideo != null)
				return false;
		} else if (!cardVideo.equals(other.cardVideo))
			return false;
		if (memorieRamMb == null) {
			if (other.memorieRamMb != null)
				return false;
		} else if (!memorieRamMb.equals(other.memorieRamMb))
			return false;
		if (procesor == null) {
			if (other.procesor != null)
				return false;
		} else if (!procesor.equals(other.procesor))
			return false;
		if (tipStocare == null) {
			if (other.tipStocare != null)
				return false;
		} else if (!tipStocare.equals(other.tipStocare))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+" " +"calculator [procesor=" + procesor + ", memorieRamMb=" + memorieRamMb + ", cardVideo=" + cardVideo
				+ ", tipStocare=" + tipStocare + ", capacitateStogareGb=" + capacitateStogareGb
				+ ", calculatoareInStatii=" + calculatoareInStatii + "]";
	}	
	
	
	
}
