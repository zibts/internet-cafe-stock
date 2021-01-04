package org;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Mobilier extends Inventar implements java.io.Serializable {
 private String tipMobilier;
 private String mobilierMaterial;
 private String componenteMobilier;
 
	@OneToMany(mappedBy = "birouStatie", cascade = ALL, orphanRemoval = true)
	private List<Statii> statiiCuBirou=new ArrayList<Statii>();
 
	
 
public Mobilier(Integer obiectId, String obiectNume, String obiectProducator, double obiectPret,
		Integer stocTotal, org.tipInventar tipInventar, String tipMobilier, String mobilierMaterial,String componenteMobilier) {
	super(obiectId, obiectNume, obiectProducator, obiectPret, stocTotal, tipInventar);
	this.tipMobilier = tipMobilier;
	this.componenteMobilier=componenteMobilier;
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


public String getComponenteMobilier() {
	return componenteMobilier;
}

public void setComponenteMobilier(String componenteMobilier) {
	this.componenteMobilier = componenteMobilier;
}

public List<Statii> getStatiiCuBirou() {
	return statiiCuBirou;
}



@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((componenteMobilier == null) ? 0 : componenteMobilier.hashCode());
	result = prime * result + ((mobilierMaterial == null) ? 0 : mobilierMaterial.hashCode());
	result = prime * result + ((statiiCuBirou == null) ? 0 : statiiCuBirou.hashCode());
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
	if (componenteMobilier == null) {
		if (other.componenteMobilier != null)
			return false;
	} else if (!componenteMobilier.equals(other.componenteMobilier))
		return false;
	if (mobilierMaterial == null) {
		if (other.mobilierMaterial != null)
			return false;
	} else if (!mobilierMaterial.equals(other.mobilierMaterial))
		return false;
	if (statiiCuBirou == null) {
		if (other.statiiCuBirou != null)
			return false;
	} else if (!statiiCuBirou.equals(other.statiiCuBirou))
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

public void seteazaBirouLaStatie(Statii s) {
	s.setBirouStatie(this);
}


}
