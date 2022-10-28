package com.hack.fse.skilltracker.exceptionhandler;

/**
 * ExceptionHandler extends the properties of Exception class and enriches with the custom error codes.
 */
public class ExceptionHandler extends Exception {
    int errorCode;
    public ExceptionHandler(int errorCode, String message){
            super(message);
            this.errorCode=errorCode;
    }
}
