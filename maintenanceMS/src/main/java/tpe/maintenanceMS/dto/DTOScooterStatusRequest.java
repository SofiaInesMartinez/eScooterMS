package tpe.maintenanceMS.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DTOScooterStatusRequest {
	@NotNull(message = "status shouldn't be null")
	@NotBlank(message = "status shouldn't be empty")
	private String status;
	
	public DTOScooterStatusRequest() {
		super();
	}
	
	public DTOScooterStatusRequest(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOScooterStatusRequest other = (DTOScooterStatusRequest) obj;
		return Objects.equals(status, other.status);
	}
}
