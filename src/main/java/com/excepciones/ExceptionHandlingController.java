package com.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    /**
     * Metodo encargado de lanzar forbidden cuando la cadena de ADN
     * no es de un mutante
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ForbidenException.class)
    public final ResponseEntity handleUserNotFoundException(ForbidenException ex,
                                                                           WebRequest request) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
