package tpe.maintenanceMS.exception;

@SuppressWarnings("serial")
public class MaintenanceAlreadyFinishedException extends Exception {
	public MaintenanceAlreadyFinishedException(long id) {
		super(String.format("The maintenance with id %s already finished", id));
	}
}
