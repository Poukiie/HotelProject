package exception;

public class ClientAlreadyHasReservationException extends Exception {
    public ClientAlreadyHasReservationException(String message) {
        super(message);
    }

    public ClientAlreadyHasReservationException() {
    }
}
