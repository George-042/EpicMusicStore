package by.georgprog.epicmusicstore.exeption.badrequest;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class GenreNotFoundException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "Genre not found";

    public GenreNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
