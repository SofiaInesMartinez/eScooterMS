package tpe.scooterMS.model;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class User implements Serializable {
	private long id;
	private String surname;
	private String email;
	private int phone;
	private int status;
	
	public User() {
		super();
	}
	
	public User(long id, String surname, String email, int phone, int status) {
		this.id = id;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, phone, status, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && phone == other.phone && status == other.status
				&& Objects.equals(surname, other.surname);
	}
}
