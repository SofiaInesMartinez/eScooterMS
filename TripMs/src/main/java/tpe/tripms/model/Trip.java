package tpe.tripms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpe.tripms.dto.TripRequestDTO;

@Entity
@Data
@NoArgsConstructor
public class Trip {
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
