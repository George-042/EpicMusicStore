package by.georgprog.epicmusicstore.exeption.unauthorized;

import by.georgprog.epicmusicstore.exeption.http.UnauthorizedException;

public class UserNotFoundException extends UnauthorizedException {

    private static final String DEFAULT_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
