package tpe.userMS.exception;

@SuppressWarnings("serial") 
public class NotFoundException extends Exception {

	public NotFoundException(String entity, long id) {
		super(String.format("The entity %s with id %s doesn't exist", entity, id));
	}
}
