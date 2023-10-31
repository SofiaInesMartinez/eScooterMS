package tpe.scooterMS.exception;

@SuppressWarnings("serial")
public class AccountWithoutMoneyException extends Exception {
	public AccountWithoutMoneyException(long idUser) {
		super(String.format("Doesn't exist an account with money owned by the user with id %s", idUser));
	}
}
