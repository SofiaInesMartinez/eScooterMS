package tpe.userMS.exception;

@SuppressWarnings("serial")
public class RoleWithNameAlreadyExistsException extends Exception {
	
	public RoleWithNameAlreadyExistsException(String name) {
		super(String.format("A role with the name %s already exists", name));
	}
}
