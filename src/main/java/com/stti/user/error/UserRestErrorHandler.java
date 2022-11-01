package com.stti.user.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice
public class UserRestErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleBindException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

                List<ApiError> errors = ex.getBindingResult().getFieldErrors().stream()
                    .map(err -> new ApiError(err.getField(),err.getRejectedValue(),err.getDefaultMessage()))
                    .distinct()
                    .collect(Collectors.toList());

                ApiErrorResponse errorResponse = new ApiErrorResponse(errors);
                ObjectMapper objMapper = new ObjectMapper();
            
                try {
                    return new ResponseEntity<>(objMapper.writeValueAsString(errorResponse),status);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleTypeMismatch(ex, headers, status, request);
    }

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
