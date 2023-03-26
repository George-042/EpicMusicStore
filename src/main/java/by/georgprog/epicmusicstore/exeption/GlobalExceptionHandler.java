package by.georgprog.epicmusicstore.exeption;

import by.georgprog.epicmusicstore.dto.ErrorDto;
import by.georgprog.epicmusicstore.exeption.http.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorDto> handleHttpException(HttpException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }
}
