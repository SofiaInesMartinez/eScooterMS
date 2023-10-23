package tpe.tripms.model;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tpe.tripms.dto.TripRequestDTO;

@Entity
@Getter
@Setter
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
	private String pauseTime; //minutes
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
	
	@Override
	public boolean equals(Object obj) {
		try {
			Trip anotherTrip = (Trip) obj;
			return this.getIdTrip() == anotherTrip.getIdTrip();
		} catch (Exception e) {
			return false;
		}
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
