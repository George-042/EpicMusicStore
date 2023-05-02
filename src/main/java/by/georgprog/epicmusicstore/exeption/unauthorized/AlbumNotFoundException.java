package by.georgprog.epicmusicstore.exeption.unauthorized;

import by.georgprog.epicmusicstore.exeption.http.UnauthorizedException;

public class AlbumNotFoundException extends UnauthorizedException {

    private static final String DEFAULT_MESSAGE = "Album not found";

    public AlbumNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
