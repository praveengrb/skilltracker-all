package com.hack.fse.skilltracker.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Maintains the application error messages.
 */
public class Error {
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private Long timestamp;
}
