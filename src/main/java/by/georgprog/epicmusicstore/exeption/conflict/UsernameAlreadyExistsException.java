package by.georgprog.epicmusicstore.exeption.conflict;

import by.georgprog.epicmusicstore.exeption.http.ConflictException;

public class UsernameAlreadyExistsException extends ConflictException {

    private static final String DEFAULT_MESSAGE = "The username is already registered.";

    public UsernameAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
