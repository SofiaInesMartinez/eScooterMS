package tpe.APIGateway.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOValidateToken {
	@NotNull(message = "token should't be null")
	@NotBlank(message = "token should't be empty")
	private String token;
	
	public DTOValidateToken() {
		super();
	}
	
	public DTOValidateToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
