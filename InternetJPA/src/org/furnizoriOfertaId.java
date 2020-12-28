package org;



public class furnizoriOfertaId implements java.io.Serializable {
	private Integer furnizor;
	private  Integer obiect;
	
	public furnizoriOfertaId() {
		super();
		this.furnizor = furnizor;
		this.obiect = obiect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((furnizor == null) ? 0 : furnizor.hashCode());
		result = prime * result + ((obiect == null) ? 0 : obiect.hashCode());
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
		furnizoriOfertaId other = (furnizoriOfertaId) obj;
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
		return true;
	}
	

}
