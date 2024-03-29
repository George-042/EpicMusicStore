package by.georgprog.epicmusicstore.exeption.http;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpException {

    public ForbiddenException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
