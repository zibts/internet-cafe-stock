package org;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity

@Inheritance(strategy = JOINED)
public class Periferice extends Inventar implements java.io.Serializable {
	private String perifericTip;
	private String perifericPort;
	
	protected Periferice(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			Integer stocDisponibil, Integer stocTotal, org.tipInventar tipInventar, String perifericTip,
			String perifericPort) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocDisponibil, stocTotal, tipInventar);
		this.perifericTip = perifericTip;
		this.perifericPort = perifericPort;
	}
	public Periferice() {
		super();
		
	}
	public String getPerifericTip() {
		return perifericTip;
	}
	public void setPerifericTip(String perifericTip) {
		this.perifericTip = perifericTip;
	}
	public String getPerifericPort() {
		return perifericPort;
	}
	public void setPerifericPort(String perifericPort) {
		this.perifericPort = perifericPort;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((perifericPort == null) ? 0 : perifericPort.hashCode());
		result = prime * result + ((perifericTip == null) ? 0 : perifericTip.hashCode());
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
		Periferice other = (Periferice) obj;
		if (perifericPort == null) {
			if (other.perifericPort != null)
				return false;
		} else if (!perifericPort.equals(other.perifericPort))
			return false;
		if (perifericTip == null) {
			if (other.perifericTip != null)
				return false;
		} else if (!perifericTip.equals(other.perifericTip))
			return false;
		return true;
	}
	
	
	
}
