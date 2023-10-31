package tpe.maintenanceMS.exception;

@SuppressWarnings("serial")
public class ScooterAlreadyInMaintenanceException extends Exception {
	public ScooterAlreadyInMaintenanceException(long id) {
		super(String.format("The scooter with id %s is already in maintenance", id));
	}
}
