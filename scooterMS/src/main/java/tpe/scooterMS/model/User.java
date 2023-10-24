package tpe.scooterMS.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class User implements Serializable {
	private long id;
	private String surname;
	private String email;
	private int phone;
	private int status;
}
