package tpe.scooterMS.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
	@Id
	private long id;
	@Column(nullable=false)
	private int phone;
	@Column(nullable=false)
	private String email;
	@Column (nullable=false)
	private String status;
}
