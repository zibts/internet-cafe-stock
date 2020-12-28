package org;

import javax.persistence.Entity;

@Entity
public class perifericMouse extends Periferice {
	private double maxDpi;
	private double greutateG;
	
	public perifericMouse(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			Integer stocDisponibil, Integer stocTotal, org.tipInventar tipInventar, String perifericTip,
			String perifericPort, double maxDpi, double greutateG) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocDisponibil, stocTotal, tipInventar, perifericTip,
				perifericPort);
		this.maxDpi = maxDpi;
		this.greutateG = greutateG;
	}
	
	public perifericMouse() {
		super();
	}

	public double getMaxDpi() {
		return maxDpi;
	}

	public void setMaxDpi(double maxDpi) {
		this.maxDpi = maxDpi;
	}

	public double getGreutateG() {
		return greutateG;
	}

	public void setGreutateG(double greutateG) {
		this.greutateG = greutateG;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(greutateG);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxDpi);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		perifericMouse other = (perifericMouse) obj;
		if (Double.doubleToLongBits(greutateG) != Double.doubleToLongBits(other.greutateG))
			return false;
		if (Double.doubleToLongBits(maxDpi) != Double.doubleToLongBits(other.maxDpi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+" "+this.getPerifericTip()+" "+this.getPerifericPort()+" " +"perifericMouse [maxDpi=" + maxDpi + ", greutateG=" + greutateG + "]";
	}
	

}
