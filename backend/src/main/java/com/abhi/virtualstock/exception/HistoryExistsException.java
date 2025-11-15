package com.abhi.virtualstock.exception;

public class HistoryExistsException extends RuntimeException {
    public HistoryExistsException(String message) {
        super(message);
    }
}
