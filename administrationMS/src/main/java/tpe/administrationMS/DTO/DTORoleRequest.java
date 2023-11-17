package tpe.administrationMS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DTORoleRequest {
	@NotNull(message = "id shouldn't be null")
	@Positive(message = "id shouldn't be negative or zero")
	private long id;
	
	@NotNull(message = "name shouldn't be null")
	@NotBlank(message = "name shouldn't be empty")
	private String name;
	
	public DTORoleRequest() {
		super();
	}
	
	public DTORoleRequest(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
