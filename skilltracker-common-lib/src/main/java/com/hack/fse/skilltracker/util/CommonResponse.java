package com.hack.fse.skilltracker.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class CommonResponse {
    public ResponseEntity formPositiveResponseEntity(Object responsePayload) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", responsePayload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity formErrResponseEntity(Object responsePayload) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", responsePayload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
