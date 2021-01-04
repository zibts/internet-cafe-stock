package org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.InheritanceType.JOINED;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import org.eclipse.persistence.annotations.JoinFetch;
import static org.eclipse.persistence.annotations.JoinFetchType.INNER;

@Entity
@Inheritance(strategy = JOINED)
public class Inventar {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer obiectId;
	private String obiectNume;
	private String obiectProducator;
	private double obiectPret;
	private Integer stocDisponibil;
	private Integer stocTotal;
	
	@ManyToOne
	@JoinColumn(name="tipId")
	private tipInventar tipInventar;
	
	@OneToMany(mappedBy = "obiectId", orphanRemoval = true, cascade = ALL)
	private List<stocInVanzare> obiecteInVanzare = new ArrayList<stocInVanzare>();
	
	@OneToMany(mappedBy = "obiect", orphanRemoval = true, cascade = ALL)
	private List<furnizoriOferta> obiecteOferite=new ArrayList<furnizoriOferta>();
	
	@ManyToMany(mappedBy= "inventarInStatie", cascade = ALL)
	private List<Statii> statiiAmplasate = new ArrayList<Statii>() ;
	
	protected Inventar(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			 Integer stocTotal, org.tipInventar tipInventar) {
		super();
		this.obiectId = obiectId;
		this.obiectNume = obiectNume;
		this.obiectProducator = obiectProducator;
		this.obiectPret = obiectPret;
		this.stocTotal = stocTotal;
		this.tipInventar = tipInventar;
		this.stocDisponibil=this.stocTotal;
	}
	
	public Inventar() {
		
	}

	public Integer getObiectId() {
		return obiectId;
	}

	public void setObiectId(Integer obiectId) {
		this.obiectId = obiectId;
	}

	public String getObiectNume() {
		return obiectNume;
	}

	public void setObiectNume(String obiectNume) {
		this.obiectNume = obiectNume;
	}

	public String getObiectProducator() {
		return obiectProducator;
	}

	public void setObiectProducator(String obiectProducator) {
		this.obiectProducator = obiectProducator;
	}

	public double getObiectPret() {
		return obiectPret;
	}

	public void setObiectPret(double obiectPret) {
		this.obiectPret = obiectPret;
	}

	public Integer getStocDisponibil() {
		return stocDisponibil;
	}

	public void setStocDisponibil(Integer stocDisponibil) {
		this.stocDisponibil=stocDisponibil;
	}

	public Integer getStocTotal() {
		return stocTotal;
	}

	public void setStocTotal(Integer stocTotal) {
		this.stocTotal = stocTotal;
	}

	public tipInventar getTipInventar() {
		return tipInventar;
	}

	public void setTipInventar(tipInventar tipInventar) {
		this.tipInventar = tipInventar;
	}
	
	public List<Statii> getStatiiAmplasate() {
		return statiiAmplasate;
	}

	public void setStatiiAmplasate(List<Statii> statiiAmplasate) {
		this.statiiAmplasate = statiiAmplasate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obiectId == null) ? 0 : obiectId.hashCode());
		result = prime * result + ((obiectNume == null) ? 0 : obiectNume.hashCode());
		long temp;
		temp = Double.doubleToLongBits(obiectPret);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((obiectProducator == null) ? 0 : obiectProducator.hashCode());
		result = prime * result + ((obiecteInVanzare == null) ? 0 : obiecteInVanzare.hashCode());
		result = prime * result + ((obiecteOferite == null) ? 0 : obiecteOferite.hashCode());
		result = prime * result + ((stocDisponibil == null) ? 0 : stocDisponibil.hashCode());
		result = prime * result + ((stocTotal == null) ? 0 : stocTotal.hashCode());
		result = prime * result + ((tipInventar == null) ? 0 : tipInventar.hashCode());
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
		Inventar other = (Inventar) obj;
		if (obiectId == null) {
			if (other.obiectId != null)
				return false;
		} else if (!obiectId.equals(other.obiectId))
			return false;
		if (obiectNume == null) {
			if (other.obiectNume != null)
				return false;
		} else if (!obiectNume.equals(other.obiectNume))
			return false;
		if (Double.doubleToLongBits(obiectPret) != Double.doubleToLongBits(other.obiectPret))
			return false;
		if (obiectProducator == null) {
			if (other.obiectProducator != null)
				return false;
		} else if (!obiectProducator.equals(other.obiectProducator))
			return false;
		if (obiecteInVanzare == null) {
			if (other.obiecteInVanzare != null)
				return false;
		} else if (!obiecteInVanzare.equals(other.obiecteInVanzare))
			return false;
		if (obiecteOferite == null) {
			if (other.obiecteOferite != null)
				return false;
		} else if (!obiecteOferite.equals(other.obiecteOferite))
			return false;
		if (stocDisponibil == null) {
			if (other.stocDisponibil != null)
				return false;
		} else if (!stocDisponibil.equals(other.stocDisponibil))
			return false;
		if (stocTotal == null) {
			if (other.stocTotal != null)
				return false;
		} else if (!stocTotal.equals(other.stocTotal))
			return false;
		if (tipInventar == null) {
			if (other.tipInventar != null)
				return false;
		} else if (!tipInventar.equals(other.tipInventar))
			return false;
		return true;
	}


	
	

	
	

}
