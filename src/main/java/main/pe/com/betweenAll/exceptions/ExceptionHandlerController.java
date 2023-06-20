package main.pe.com.betweenAll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    //NO encuentra el objeto en al base de datos
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageGeneric resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return message;
    }

    //Datos incompletos
    @ExceptionHandler(IncompleteDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessageGeneric incompleteDataExceptionHandler(IncompleteDataException ex, WebRequest webRequest){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return message;
    }

    //no hay datos repetidos por ejemplo el DNI
    @ExceptionHandler(KeyRepeatedDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessageGeneric keyRepeatedDataExceptionHandler(KeyRepeatedDataException ex, WebRequest webRequest){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return message;
    }

    //exepcion general
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageGeneric globalExceptionHandler(RuntimeException ex, WebRequest request) {
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return message;
    }

}
