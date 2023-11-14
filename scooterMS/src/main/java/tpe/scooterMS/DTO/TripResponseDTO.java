package tpe.scooterMS.DTO;

import java.util.Date;
import java.util.Objects;

import tpe.scooterMS.model.Trip;

public class TripResponseDTO {
	private long idTrip;
	private long idUser;
	private long idScooter;
	private float kms;
	private float amount;
	private Date startDate;
	private Date endDate;
	
	public TripResponseDTO(Trip trip) {
		this.idTrip = trip.getIdTrip();
		this.idUser = trip.getIdUser();
		this.idScooter = trip.getScooter().getId();
		this.kms = trip.getKms();
		this.amount = trip.getTripAmount();
		this.startDate = trip.getStartDate();
		this.endDate = trip.getEndDate();
	}
	
	

	public TripResponseDTO(long idTrip, long idUser, long idScooter, float kms, float amount, Date startDate,
			Date endDate) {
		super();
		this.idTrip = idTrip;
		this.idUser = idUser;
		this.idScooter = idScooter;
		this.kms = kms;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(long idTrip) {
		this.idTrip = idTrip;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdScooter() {
		return idScooter;
	}

	public void setIdScooter(long idScooter) {
		this.idScooter = idScooter;
	}

	public float getKms() {
		return kms;
	}

	public void setKms(float kms) {
		this.kms = kms;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, idScooter, idTrip, idUser, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripResponseDTO other = (TripResponseDTO) obj;
		return Objects.equals(endDate, other.endDate) && idScooter == other.idScooter && idTrip == other.idTrip
				&& idUser == other.idUser && Objects.equals(startDate, other.startDate);
	}
}