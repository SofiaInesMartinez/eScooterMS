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
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class Trip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrip;
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
//	private Timer cronometer; comentado por ahora
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Scooter scooter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Stop originStop;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Stop destinationStop;
	
	public Trip(User user, Scooter scooter, Stop originStop) {
		this.startDate = new Date(System.currentTimeMillis());
		this.kms = 0;
		this.tripAmount = 0;
		this.pauseTime = "0:0";
		this.user = user;
		this.scooter = scooter;
		this.originStop = originStop;
	}
}
