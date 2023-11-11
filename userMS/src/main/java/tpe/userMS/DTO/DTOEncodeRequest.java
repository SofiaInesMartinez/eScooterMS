package tpe.userMS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOEncodeRequest {
	@NotNull(message = "password shouldn't be null")
	@NotBlank(message = "password shouldn't be empty")
	private String password;
	
	public DTOEncodeRequest() {
		super();
	}
	
	public DTOEncodeRequest(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
