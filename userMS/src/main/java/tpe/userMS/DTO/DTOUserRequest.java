package tpe.userMS.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public class DTOUserRequest {
	@NotNull(message = "id shouldn't be null")
	private long id;
	@NotNull(message = "phone shouldn't be null")
	@NotEmpty(message = "phone shouldn't be empty")
	private int phone;

	@NotNull(message = "email shouldn't be null")
	@NotEmpty(message = "email shouldn't be empty")
	private String email;

	@NotNull(message = "name shouldn't be null")
	@NotEmpty(message = "name shouldn't be empty")
	private String name;

	@NotNull(message = "surname shouldn't be null")
	@NotEmpty(message = "surname shouldn't be empty")
	private String surname;

	@NotNull(message = "username shouldn't be null")
	@NotEmpty(message = "username shouldn't be empty")
	private String username;

	@NotNull(message = "role shouldn't be null")
	@NotEmpty(message = "role shouldn't be empty")
	private String role;

	public DTOUserRequest(long id, int phone, String email, String name, String surname, String username, String role) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
