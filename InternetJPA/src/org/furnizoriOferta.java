package org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import static javax.persistence.CascadeType.ALL;

@Entity
@IdClass(furnizoriOfertaId.class)
public class furnizoriOferta  {

	private double pretFurnizoriLei;
	
	private Integer cantitateOferita;
	
	@Id
	@ManyToOne(cascade = ALL)
	@JoinColumn(name="furnizorId")
	private Furnizori furnizor;
	
	@Id
	@ManyToOne(cascade = ALL)
	@JoinColumn(name="obiectId")
	private Inventar obiect;

	public furnizoriOferta(double pretFurnizoriLei, Integer cantitateOferita, Furnizori furnizor, Inventar obiect) {
		super();
		this.pretFurnizoriLei = pretFurnizoriLei;
		this.cantitateOferita = cantitateOferita;
		this.furnizor = furnizor;
		this.obiect = obiect;
	}
	
	public furnizoriOferta() {
		super();
	}
	
	public double getPretFurnizoriLei() {
		return pretFurnizoriLei;
	}
	public void setPretFurnizoriLei(double pretFurnizoriLei) {
		this.pretFurnizoriLei = pretFurnizoriLei;
	}
	public Integer getCantitateOferita() {
		return cantitateOferita;
	}
	public void setCantitateOferita(Integer cantitateOferita) {
		this.cantitateOferita = cantitateOferita;
	}
	public Furnizori getFurnizor() {
		return furnizor;
	}
	public void setFurnizor(Furnizori furnizor) {
		this.furnizor = furnizor;
	}
	public Inventar getObiect() {
		return obiect;
	}
	public void setObiect(Inventar obiect) {
		this.obiect = obiect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantitateOferita == null) ? 0 : cantitateOferita.hashCode());
		result = prime * result + ((furnizor == null) ? 0 : furnizor.hashCode());
		result = prime * result + ((obiect == null) ? 0 : obiect.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pretFurnizoriLei);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		furnizoriOferta other = (furnizoriOferta) obj;
		if (cantitateOferita == null) {
			if (other.cantitateOferita != null)
				return false;
		} else if (!cantitateOferita.equals(other.cantitateOferita))
			return false;
		if (furnizor == null) {
			if (other.furnizor != null)
				return false;
		} else if (!furnizor.equals(other.furnizor))
			return false;
		if (obiect == null) {
			if (other.obiect != null)
				return false;
		} else if (!obiect.equals(other.obiect))
			return false;
		if (Double.doubleToLongBits(pretFurnizoriLei) != Double.doubleToLongBits(other.pretFurnizoriLei))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "furnizoriOferta [pretFurnizoriLei=" + pretFurnizoriLei + ", cantitateOferita=" + cantitateOferita
				+ ", furnizor=" + furnizor + ", obiect=" + obiect + "]";
	}
	
	
	
	
	
	

}
