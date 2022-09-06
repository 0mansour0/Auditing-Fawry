package com.example.auditing.exception;

public class DbResultNotFoundException extends RuntimeException {
    public DbResultNotFoundException(String message) {
        super(message);
    }
}