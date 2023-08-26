package by.georgprog.epicmusicstore.exeption.badrequest;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class AlbumNotFoundException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "Album not found";

    public AlbumNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
