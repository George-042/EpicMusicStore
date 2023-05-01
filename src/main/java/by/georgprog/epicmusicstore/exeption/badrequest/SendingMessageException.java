package by.georgprog.epicmusicstore.exeption.badrequest;

import by.georgprog.epicmusicstore.exeption.http.BadRequestException;

public class SendingMessageException extends BadRequestException {

    private static final String DEFAULT_MESSAGE = "Sending message error.";

    public SendingMessageException() {
        super(DEFAULT_MESSAGE);
    }

    public SendingMessageException(String s) {
        super(s);
    }
}
