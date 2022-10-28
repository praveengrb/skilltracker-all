package com.hack.fse.skilltracker.exceptionhandler;

/**
 *  InEligibleForUpdateException is thrown when the given user id is not valid/ present in the database.
 */
public class InEligibleForUpdateException extends ExceptionHandler {
    public InEligibleForUpdateException(ErrorCodes errorCodes){
        super(errorCodes.value(),errorCodes.getReasonPhrase());
    }
}
