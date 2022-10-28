package com.hack.fse.skilltracker.exceptionhandler;

public enum ErrorCodes {

    IE_001(100,"Profile is ineligible for update."),
    UID_100(400,"Given User ID is invalid."),
    UID_101(400,"Duplicate User.");
    private final int value;
    private final String reasonPhrase;
    ErrorCodes(int value, String reasonPhrase){
        this.value=value;
        this.reasonPhrase=reasonPhrase;
    }
    /**
     * Getter for property 'reasonPhrase'.
     *
     * @return Value for property 'reasonPhrase'.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
    public int value() {
        return this.value;
    }
}
