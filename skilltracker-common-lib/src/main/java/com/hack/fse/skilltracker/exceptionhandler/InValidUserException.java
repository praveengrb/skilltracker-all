package com.hack.fse.skilltracker.exceptionhandler;

/**
 *  InValidUserException is thrown when the given user id is not valid/ present in the database.
 */
public class InValidUserException extends ExceptionHandler {
    public InValidUserException(ErrorCodes errorCodes){
        super(errorCodes.value(),errorCodes.getReasonPhrase());
    }
}
