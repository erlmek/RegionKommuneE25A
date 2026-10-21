package org.example.regionkommunee25a.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        System.out.println("GLOBAL EXCEPTION KALDT");
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage() + " name:" + ex.getClass().getName(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorMessage> globalArithmeticExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage() + " Du dividerede lige med nul",
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 1. Fanger konverteringsfejl (f.eks. String til int) i URL-parametre/stier
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String msg = " Feltet '" + ex.getName() + "' med værdien '" + ex.getValue() + "' kunne ikke konverteres.";

        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage() + msg,
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
