package krados.oose.assignment.controller.exceptions;

public class ItemException extends Exception {
    public ItemException(String message) {
        super(message);
    }
    public ItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
