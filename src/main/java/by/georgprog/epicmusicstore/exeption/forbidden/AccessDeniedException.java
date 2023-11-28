package by.georgprog.epicmusicstore.exeption.forbidden;

import by.georgprog.epicmusicstore.exeption.http.ForbiddenException;

public class AccessDeniedException extends ForbiddenException {

    private static final String DEFAULT_MESSAGE = "Access denied";

    public AccessDeniedException() {
        super(DEFAULT_MESSAGE);
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
