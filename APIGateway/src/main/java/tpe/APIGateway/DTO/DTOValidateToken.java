package tpe.APIGateway.DTO;

import java.util.List;

public class DTOValidateToken {
    private boolean isAuthenticated;
    private String username;
    private List<String> authorities;
    
	public DTOValidateToken() {
		super();
	}

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
