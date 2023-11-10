package tpe.userMS.exception;

@SuppressWarnings("serial")
public class RoleWithNameNotFoundException extends Exception {

	public RoleWithNameNotFoundException(String name) {
		super(String.format("Role with the name %s not found", name));
	}
}
