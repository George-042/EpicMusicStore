package by.georgprog.epicmusicstore.exeption.http;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
