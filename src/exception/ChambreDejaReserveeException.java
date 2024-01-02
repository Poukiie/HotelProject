package exception;

public class ChambreDejaReserveeException extends Exception {
    public ChambreDejaReserveeException(String message) {
        super(message);
    }

    public ChambreDejaReserveeException() {
    }
}
