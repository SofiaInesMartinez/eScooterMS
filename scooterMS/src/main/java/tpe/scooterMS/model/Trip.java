package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tpe.scooterMS.DTO.TripRequestDTO;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class Trip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrip;
	@Column(nullable = false)
	private int idUser;
	@Column(nullable = false)
	private int idScooter;
	@Column(nullable = false)
	private int idOriginStop;
	@Column
	private int idDestinationStop; // init with 0 >:( and cannot be Integer
	@Column(nullable = false)
	private Date startDate;
	@Column
	private Date endDate;
	@Column(nullable = false)
	private float kms;
	@Column(nullable = false)
	private float tripAmount;
	@Column(nullable = false)
	private String pauseTime; //minutes
	
	//CREAR CRONOMETRO ACA Y PERSISTIR
	private Timer cronometer;
	
	
//	@Column
//	private long timerId;
	
	public Trip(TripRequestDTO request) throws InterruptedException {
		this.idUser = request.getIdUser();
		this.idScooter = request.getIdScooter();
		this.idOriginStop = request.getIdOriginStop();
		this.startDate = new Date(System.currentTimeMillis());
		this.kms = 0;
		this.tripAmount = 0;
		this.pauseTime = "0:0";
//		this.timerId = -1;
	}


	public int getIdTrip() {
		return idTrip;
	}


	public void setIdTrip(int idTrip) {
		this.idTrip = idTrip;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public int getIdScooter() {
		return idScooter;
	}


	public void setIdScooter(int idScooter) {
		this.idScooter = idScooter;
	}


	public int getIdOriginStop() {
		return idOriginStop;
	}


	public void setIdOriginStop(int idOriginStop) {
		this.idOriginStop = idOriginStop;
	}


	public int getIdDestinationStop() {
		return idDestinationStop;
	}


	public void setIdDestinationStop(int idDestinationStop) {
		this.idDestinationStop = idDestinationStop;
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


	public float getKms() {
		return kms;
	}


	public void setKms(float kms) {
		this.kms = kms;
	}


	public float getTripAmount() {
		return tripAmount;
	}


	public void setTripAmount(float tripAmount) {
		this.tripAmount = tripAmount;
	}


	public String getPauseTime() {
		return pauseTime;
	}


	public void setPauseTime(String pauseTime) {
		this.pauseTime = pauseTime;
	}


	public Timer getCronometer() {
		return cronometer;
	}


	public void setCronometer(Timer cronometer) {
		this.cronometer = cronometer;
	}
	
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private User user;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Scooter scooter;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Stop originStop;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Stop destinationStop;
}
