package org;

import javax.persistence.Entity;

@Entity
public class perifericKeyboard extends Periferice{
	private Boolean prezentBacklit;
	private Boolean esteMehanica;
	
	public perifericKeyboard(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
			Integer stocDisponibil, Integer stocTotal, org.tipInventar tipInventar, String perifericTip,
			String perifericPort, Boolean prezentBacklit, Boolean esteMehanica) {
		super(obiectId, obiectNume, obiectProducator, obiectPret, stocDisponibil, stocTotal, tipInventar, perifericTip,
				perifericPort);
		this.prezentBacklit = prezentBacklit;
		this.esteMehanica = esteMehanica;
	}

	public perifericKeyboard() {
		super();
	}

	public Boolean getPrezentBacklit() {
		return prezentBacklit;
	}

	public void setPrezentBacklit(Boolean prezentBacklit) {
		this.prezentBacklit = prezentBacklit;
	}

	public Boolean getEsteMehanica() {
		return esteMehanica;
	}

	public void setEsteMehanica(Boolean esteMehanica) {
		this.esteMehanica = esteMehanica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((esteMehanica == null) ? 0 : esteMehanica.hashCode());
		result = prime * result + ((prezentBacklit == null) ? 0 : prezentBacklit.hashCode());
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
		perifericKeyboard other = (perifericKeyboard) obj;
		if (esteMehanica == null) {
			if (other.esteMehanica != null)
				return false;
		} else if (!esteMehanica.equals(other.esteMehanica))
			return false;
		if (prezentBacklit == null) {
			if (other.prezentBacklit != null)
				return false;
		} else if (!prezentBacklit.equals(other.prezentBacklit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+" "+this.getPerifericTip()+" "+this.getPerifericPort()+" " +"perifericKeyboard [prezentBacklit=" + prezentBacklit + ", esteMehanica=" + esteMehanica + "]";
	}
	

}
