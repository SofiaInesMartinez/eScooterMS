package tpe.userMS.DTO;

@SuppressWarnings("serial")
public class InvalidRolesRequestException extends Exception {

	public InvalidRolesRequestException() {
		super("Invalid roles entered");
	}
}
