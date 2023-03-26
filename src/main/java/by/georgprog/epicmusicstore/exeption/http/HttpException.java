package by.georgprog.epicmusicstore.exeption.http;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends Exception {

    public HttpException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
