package com.stti.user.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class UserRestErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            org.springframework.http.HttpHeaders headers, org.springframework.http.HttpStatus status,
            WebRequest request) {
                StringBuilder builder = new StringBuilder();
                builder.append(ex.getContentType());
                builder.append(" media type is not supported. Supported media types are ");
                ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
                return new ResponseEntity<>(builder.toString(),status);
           }

    

    
}
