package tpe.maintenanceMS.exception;

@SuppressWarnings("serial")
public class ServiceCommunicationException extends Exception {
	public ServiceCommunicationException() {
		super("Is not possible to communicate with the service");
	}
}
