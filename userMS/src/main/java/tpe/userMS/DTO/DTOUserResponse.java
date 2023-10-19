package tpe.userMS.DTO;

import tpe.userMS.model.User;

public class DTOUserResponse {
	private String surname;
	private String email;
	private int phone;
	
	
	public DTOUserResponse(User user) {
		super();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.phone = user.getPhone();
	}
	
	
	public DTOUserResponse(String surname, String email, int phone) {
		super();
		this.surname = surname;
		this.email = email;
		this.phone = phone;
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
	@Override
	public String toString() {
		return "DTOUserResponse [surname=" + surname + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
