package tpe.APIGateway.DTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOValidateToken {
	@NotNull(message = "token should't be null")
	@NotBlank(message = "token should't be empty")
	//private String token;
    private boolean isAuthenticated;
    private String username;
    private List<String> authorities;
    
	public DTOValidateToken() {
		super();
	}
	/*
	public DTOValidateToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
*/
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	
}
