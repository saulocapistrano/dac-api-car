package com.ads.dacapicar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

public ResponseEntity<?> handleCarNotFoundException(CarNotFoundException exception, WebRequest webRequest){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
}


}
