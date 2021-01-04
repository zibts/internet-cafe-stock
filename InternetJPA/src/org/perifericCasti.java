package org;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity 
public class perifericCasti extends Periferice {
	
	private Boolean stereo;
	private Boolean castiMicrofon;
	
	@OneToMany(mappedBy = "castiStatieId", cascade = ALL, orphanRemoval = true)
	private List<Statii> statiiCuCasti=new ArrayList<Statii>();
	
	public perifericCasti(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			 Integer stocTotal, org.tipInventar tipInventar, String perifericTip,
			String perifericPort, Boolean stereo, Boolean castiMicrofon) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocTotal, tipInventar, perifericTip,
				perifericPort);
		this.stereo = stereo;
		this.castiMicrofon = castiMicrofon;
	}
	public perifericCasti() {
		
	}
	public Boolean getStereo() {
		return stereo;
	}
	public void setStereo(Boolean stereo) {
		this.stereo = stereo;
	}
	public Boolean getCastiMicrofon() {
		return castiMicrofon;
	}
	public void setCastiMicrofon(Boolean castiMicrofon) {
		this.castiMicrofon = castiMicrofon;
	}
	public List<Statii> getStatiiCuCasti() {
		return statiiCuCasti;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((castiMicrofon == null) ? 0 : castiMicrofon.hashCode());
		result = prime * result + ((stereo == null) ? 0 : stereo.hashCode());
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
		perifericCasti other = (perifericCasti) obj;
		if (castiMicrofon == null) {
			if (other.castiMicrofon != null)
				return false;
		} else if (!castiMicrofon.equals(other.castiMicrofon))
			return false;
		if (stereo == null) {
			if (other.stereo != null)
				return false;
		} else if (!stereo.equals(other.stereo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+" "+this.getPerifericTip()+" "+this.getPerifericPort()+" " +"perifericCasti [stereo=" + stereo + ", castiMicrofon=" + castiMicrofon + "]";
	}
	
	public void seteazaCastiLaStatie(Statii s) {
		s.setCastiStatieId(this);
	}
	

}
