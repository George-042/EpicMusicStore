package by.georgprog.epicmusicstore.exeption.conflict;

import by.georgprog.epicmusicstore.exeption.http.ConflictException;

public class EmailAlreadyExistsException extends ConflictException {

    private static final String DEFAULT_MESSAGE = "The email address is already registered.";

    public EmailAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
