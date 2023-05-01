package by.georgprog.epicmusicstore.exeption.unauthorized;

import by.georgprog.epicmusicstore.exeption.http.UnauthorizedException;

public class UsernameOrEmailNotFoundException extends UnauthorizedException {

    private static final String DEFAULT_MESSAGE = "Username or email not found";

    public UsernameOrEmailNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UsernameOrEmailNotFoundException(String msg) {
        super(msg);
    }
}
