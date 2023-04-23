package by.georgprog.epicmusicstore.exeption.http;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {

    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
