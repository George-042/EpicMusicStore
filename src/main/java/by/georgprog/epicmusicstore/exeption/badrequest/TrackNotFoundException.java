package by.georgprog.epicmusicstore.exeption.badrequest;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class TrackNotFoundException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "Track not found";

    public TrackNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TrackNotFoundException(String message) {
        super(message);
    }
}
