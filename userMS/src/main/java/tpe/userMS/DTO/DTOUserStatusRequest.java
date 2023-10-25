package tpe.userMS.DTO;

import java.util.Objects;

public class DTOUserStatusRequest {
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
