package by.georgprog.epicmusicstore.exeption.badrequest;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class PlaylistNotFoundException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "Playlist not found";

    public PlaylistNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
