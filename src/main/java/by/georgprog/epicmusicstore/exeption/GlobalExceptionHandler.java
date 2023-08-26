package by.georgprog.epicmusicstore.exeption;

import by.georgprog.epicmusicstore.dto.error.ErrorDto;
import by.georgprog.epicmusicstore.exeption.http.BadRequestException;
import by.georgprog.epicmusicstore.exeption.http.ConflictException;
import by.georgprog.epicmusicstore.exeption.http.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleHttpException(BadRequestException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDto> handleHttpException(ConflictException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDto> handleUsernameNotFoundException(UnauthorizedException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }
}
