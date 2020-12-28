package org;

import javax.persistence.Entity;

@Entity
public class Mobilier extends Inventar implements java.io.Serializable {
 private String tipMobilier;
 private String mobilierMaterial;
 
 
public Mobilier(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret, Integer stocDisponibil,
		Integer stocTotal, org.tipInventar tipInventar, String tipMobilier, String mobilierMaterial) {
	super(obiectId, obiectNume, obiectProducator, obiectPret, stocDisponibil, stocTotal, tipInventar);
	this.tipMobilier = tipMobilier;
	this.mobilierMaterial = mobilierMaterial;
}

public Mobilier() {
	super();
}

public String getTipMobilier() {
	return tipMobilier;
}

public void setTipMobilier(String tipMobilier) {
	this.tipMobilier = tipMobilier;
}

public String getMobilierMaterial() {
	return mobilierMaterial;
}

public void setMobilierMaterial(String mobilierMaterial) {
	this.mobilierMaterial = mobilierMaterial;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((mobilierMaterial == null) ? 0 : mobilierMaterial.hashCode());
	result = prime * result + ((tipMobilier == null) ? 0 : tipMobilier.hashCode());
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
	Mobilier other = (Mobilier) obj;
	if (mobilierMaterial == null) {
		if (other.mobilierMaterial != null)
			return false;
	} else if (!mobilierMaterial.equals(other.mobilierMaterial))
		return false;
	if (tipMobilier == null) {
		if (other.tipMobilier != null)
			return false;
	} else if (!tipMobilier.equals(other.tipMobilier))
		return false;
	return true;
}

@Override
public String toString() {
	return ""+ this.getObiectId() +" "+ this.getObiectNume()+" "+this.getObiectProducator()+" "+ this.getObiectPret()+" "+ this.getStocDisponibil()+"Mobilier [tipMobilier=" + tipMobilier + ", mobilierMaterial=" + mobilierMaterial + "]";
}




}
