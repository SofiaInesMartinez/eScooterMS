package tpe.administrationMS.exception;

@SuppressWarnings("serial")
public class InvalidRolesRequestException extends Exception {

	public InvalidRolesRequestException() {
		super("Invalid roles entered");
	}
}
