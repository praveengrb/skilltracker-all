package com.hack.fse.skilltracker.exceptionhandler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class ErrorHandler {

    public List<String> handleArgumentException(Errors errors){
        List<String> errorsMessages=errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return errorsMessages;
    }
    public ResponseEntity<Object> formErrorResponseObject(List<String> errorsMessages){
        Map<String,Object> response=new HashMap<>();
        response.put("errors",errorsMessages);
          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
