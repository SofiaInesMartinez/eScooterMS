package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Trip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private Date startDate;
	@Column
	private Date endDate;
	@Column(nullable = false)
	private float kms;
	@Column(nullable = false)
	private float tripAmount;
	@Column(nullable = false)
	private long pauseTime; //minutos
	@Column(nullable = false)
	private long idUser;
	
	//CREAR CRONOMETRO ACA Y PERSISTIR
//	private Timer cronometer; comentado por ahora
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Scooter scooter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Stop originStop;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Stop destinationStop;
	
	public Trip() {
		super();
	}
	
	public Trip(long idUser, Scooter scooter, Stop originStop) {
		this.startDate = new Date(System.currentTimeMillis());
		this.kms = 0;
		this.tripAmount = 0;
		this.pauseTime = 15;
		this.idUser = idUser;
		this.scooter = scooter;
		this.originStop = originStop;
	}


	public int getIdTrip() {
		return id;
	}


	public void setIdTrip(int idTrip) {
		this.id = idTrip;
	}


	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
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


	public long getPauseTime() {
		return pauseTime;
	}


	public void setPauseTime(long pauseTime) {
		this.pauseTime = pauseTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Scooter getScooter() {
		return scooter;
	}


	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}


	public Stop getOriginStop() {
		return originStop;
	}


	public void setOriginStop(Stop originStop) {
		this.originStop = originStop;
	}


	public Stop getDestinationStop() {
		return destinationStop;
	}


	public void setDestinationStop(Stop destinationStop) {
		this.destinationStop = destinationStop;
	}


//	public Timer getCronometer() {
//		return cronometer;
//	}
//
//
//	public void setCronometer(Timer cronometer) {
//		this.cronometer = cronometer;
//	}
}
