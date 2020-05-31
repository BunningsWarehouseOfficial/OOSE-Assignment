package krados.oose.assignment.controller.exceptions;

public class UpdaterException extends Throwable {
    public UpdaterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdaterException(String message) {
        super(message);
    }
}
