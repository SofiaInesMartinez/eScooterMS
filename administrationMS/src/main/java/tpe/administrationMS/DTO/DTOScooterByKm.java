package tpe.administrationMS.DTO;

import java.util.Objects;

public class DTOScooterByKm {
	private long idScooter;
	private float kilometers;
	
	public DTOScooterByKm() {
		super();
	}
	
	public DTOScooterByKm(long id, float kilometers) {
		super();
		this.idScooter = id;
		this.kilometers = kilometers;
	}
	

	public long getIdScooter() {
		return idScooter;
	}

	public void setIdScooter(long idScooter) {
		this.idScooter = idScooter;
	}

	public float getKilometers() {
		return kilometers;
	}

	public void setKilometers(float kilometers) {
		this.kilometers = kilometers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idScooter, kilometers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOScooterByKm other = (DTOScooterByKm) obj;
		return idScooter == other.idScooter
				&& Float.floatToIntBits(kilometers) == Float.floatToIntBits(other.kilometers);
	}

}
