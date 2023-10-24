package tpe.scooterMS.DTO;

import java.sql.Date;

import lombok.Data;
import tpe.scooterMS.model.Scooter;

@Data
public class ScooterByKmsDTO {
	private long idScooter;
	private float kilometers;
	
	public ScooterByKmsDTO(long id, float kilometers) {
		super();
		this.idScooter = id;
		this.kilometers = kilometers;
	}
	
	public ScooterByKmsDTO(Scooter scooter) {
		super();
		this.idScooter = scooter.getId();
		this.kilometers = scooter.getKms();
	}
	
	public float getKms() {
		return kilometers;
	}

	public void setKms(float kilometers) {
		this.kilometers = kilometers;
	}
	public long getId() {
		return idScooter;
	}
	public void setId(long id) {
		this.idScooter = id;
	}
}
