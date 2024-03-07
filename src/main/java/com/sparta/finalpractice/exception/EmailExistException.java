package com.sparta.finalpractice.exception;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String message) {
        super(message);
    }
}
