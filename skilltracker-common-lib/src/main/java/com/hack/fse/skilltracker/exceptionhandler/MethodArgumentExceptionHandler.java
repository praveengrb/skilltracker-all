package com.hack.fse.skilltracker.exceptionhandler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MethodArgumentExceptionHandler  extends ResponseEntityExceptionHandler {
    /** {@inheritDoc} */
    @Override

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            @NotNull HttpStatus status,
            WebRequest request) {
        Map<String,Object> response=new HashMap<>();



List<String> errorsMessages=exception.getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
return new ResponseEntity<>(errorsMessages,HttpStatus.BAD_REQUEST);

    }

    private String extractValidationMessage(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception.getMessage();
        String[] messageParts = exceptionMessage.split(";");
        String finalPart = messageParts[messageParts.length -1];

        return finalPart.trim().replaceAll("default message \\[|]]","");
    }



}
