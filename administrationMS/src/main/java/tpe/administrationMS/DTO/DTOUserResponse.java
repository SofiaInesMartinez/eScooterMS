package tpe.administrationMS.DTO;


public class DTOUserResponse {
	private long id;
	private String surname;
	private String email;
	private int phone;
	private int status;
	
	
	public DTOUserResponse() {
		super();
	}

	public DTOUserResponse(long id, String surname, String email, int phone, int status) {
		super();
		this.id = id;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.status = status;
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
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "DTOUserResponse [surname=" + surname + ", email=" + email + ", phone=" + phone + ", status=" + status +"]";
	}
	
}
