package tpe.administrationMS.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DTOAuthRequest {

    @NotNull( message = "email should0t be null" )
    @NotEmpty( message = "email should0t be empty" )
    private String email;

    @NotNull( message = "email should0t be null")
    @NotEmpty( message = "email should0t be empty" )
    @Size(min = 4, max = 100, message = "password should have a minimun of 4 characters and a maximum of 100")
    private String password;

    public DTOAuthRequest() {
    	super();
    }
    
    public DTOAuthRequest(String email, String password) {
    	this.email = email;
    	this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
