package com.abhi.virtualstock.exception;

public class InvalidArticleException extends RuntimeException {
    public InvalidArticleException(String message) {
        super(message);
    }
}
