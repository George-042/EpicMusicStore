package by.georgprog.epicmusicstore.exeption.forbidden;

import by.georgprog.epicmusicstore.exeption.http.ForbiddenException;

public class ObtainingDataException extends ForbiddenException {

    private static final String DEFAULT_MESSAGE = "The data is not obtained.";

    public ObtainingDataException() {
        super(DEFAULT_MESSAGE);
    }

    public ObtainingDataException(String message) {
        super(message);
    }
}
