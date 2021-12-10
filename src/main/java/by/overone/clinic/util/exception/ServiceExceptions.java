package by.overone.clinic.util.exception;

public class ServiceExceptions extends Exception{
    public ServiceExceptions(String message) {
        super(message);
    }

    public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
