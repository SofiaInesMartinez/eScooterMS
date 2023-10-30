package tpe.scooterMS.DTO;

import java.util.Objects;

import tpe.scooterMS.model.Scooter;

public class ScooterByKmsDTO {
	private long idScooter;
	private float kilometers;
	
	public ScooterByKmsDTO() {
		super();
	}
	
	public ScooterByKmsDTO(long id, float kilometers) {
		super();
		this.idScooter = id;
		this.kilometers = kilometers;
	}
	
	public ScooterByKmsDTO(Scooter scooter) {
		super();
		this.idScooter = scooter.getId();
		this.kilometers = scooter.getKilometers();
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
		ScooterByKmsDTO other = (ScooterByKmsDTO) obj;
		return idScooter == other.idScooter
				&& Float.floatToIntBits(kilometers) == Float.floatToIntBits(other.kilometers);
	}
}
