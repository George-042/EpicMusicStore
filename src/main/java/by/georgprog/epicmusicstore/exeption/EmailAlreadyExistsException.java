package by.georgprog.epicmusicstore.exeption;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "The email address is already registered.";

    public EmailAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
