package by.georgprog.epicmusicstore.exeption.unauthorized;

import by.georgprog.epicmusicstore.exeption.http.UnauthorizedException;

public class InvalidPasswordException extends UnauthorizedException {

    private static final String DEFAULT_MESSAGE = "Invalid password";

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
