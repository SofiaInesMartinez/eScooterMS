package tpe.tripms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import tpe.tripms.dto.TripRequestDTO;
import tpe.tripms.dto.TripResponseDTO;

@Entity
@Data
@IdClass(TripPK.class)
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrip;
	@Id
	private int idUser;
	@Id
	private int idScooter;
	@Column(nullable = false)
	private int idOriginStop;
	@Column
	private int idDestinationStop;
	@Column(nullable = false)
	private Date startDate;
	@Column
	private Date endDate;
	@Column(nullable = false)
	private float kms;
	@Column(nullable = false)
	private float tripAmount;
	@Column(nullable = false)
	private float pauseTime; //minutes
	
	public Trip(TripRequestDTO request) {
		this.idUser = request.getIdUser();
		this.idScooter = request.getIdScooter();
		this.idOriginStop = request.getIdOriginStop();
		this.startDate = new Date(System.currentTimeMillis());
		this.kms = 0;
		this.tripAmount = 0;
		this.pauseTime = 0;
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
