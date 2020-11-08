package com.camaraturismoorosi.apicamaraturismoorosi.directory;

public class Error {

    private String errorId;
    private String errorMessage;

    public Error(String errorId, String errorMessage) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
