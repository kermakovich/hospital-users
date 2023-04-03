package solvd.laba.ermakovich.hu.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import solvd.laba.ermakovich.hu.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.web.dto.ErrorDto;
import solvd.laba.ermakovich.hu.web.dto.FieldErrorDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ermakovich Kseniya
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            errors.add(new FieldErrorDto(fieldError.getField(), error.getDefaultMessage()));
        });
        return errors;
    }

    @ExceptionHandler({IllegalOperationException.class, ResourceAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEntityAlreadyExistsException(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleOtherException(Exception ex) {
        log.error(ex.getMessage(), ex.getClass());
        return new ErrorDto("something is wrong, please, try later");
    }

}
