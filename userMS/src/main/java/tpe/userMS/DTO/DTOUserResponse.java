package tpe.userMS.DTO;

import java.util.List;

import tpe.userMS.model.Role;
import tpe.userMS.model.User;

public class DTOUserResponse {
	private long id;
	private String surname;
	private String email;
	private List<Role> roles;
	private int status;
	
	
	public DTOUserResponse(User user) {
		super();
		this.id = user.getId();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.roles = user.getRoles();
		this.status = user.getStatus();
	}
	
	
	public DTOUserResponse(long id, String surname, String email, List<Role> roles, int status) {
		super();
		this.id = id;
		this.surname = surname;
		this.email = email;
		this.roles = roles;
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
	public long getId() {
		return id;
	}
	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
		return "DTOUserResponse [id=" + id + ", surname=" + surname + ", email=" + email + ", roles=" + roles
				+ ", status=" + status + "]";
	}
}
