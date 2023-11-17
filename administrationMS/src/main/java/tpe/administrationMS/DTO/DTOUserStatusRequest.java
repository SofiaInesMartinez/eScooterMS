package tpe.administrationMS.DTO;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DTOUserStatusRequest {
	@NotNull(message = "status shouldn't be null")
	@Min(0)
	@Max(1)
	private int status;
	
	public DTOUserStatusRequest() {
		super();
	}
	
	public DTOUserStatusRequest(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
		DTOUserStatusRequest other = (DTOUserStatusRequest) obj;
		return Objects.equals(status, other.status);
	}
}
