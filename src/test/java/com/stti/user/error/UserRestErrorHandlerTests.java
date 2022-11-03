package com.stti.user.error;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

public class UserRestErrorHandlerTests {

    UserRestErrorHandler errorHandler = new UserRestErrorHandler();
    WebRequest webRequest = mock(WebRequest.class);
    HttpHeaders headers = mock(HttpHeaders.class);

    @Test
    public void testHandleMethodArgumentNotValidUsernameMissing(){
        String result = "{\"errorList\":[{\"fieldName\":\"username\",\"rejectedValue\":null,\"errorMsg\":\"must not be null\"}]}";

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        FieldError fieldError = mock(FieldError.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(fieldError.getField()).thenReturn("username");
        when(fieldError.getRejectedValue()).thenReturn(null);
        when(fieldError.getDefaultMessage()).thenReturn("must not be null");
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(ex.getBindingResult().getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Object> res = errorHandler.handleMethodArgumentNotValid(ex, headers, HttpStatus.BAD_REQUEST, webRequest);
        assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(res.getBody().toString(), result);

    }

    
    
}
