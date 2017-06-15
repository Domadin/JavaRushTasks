package com.javarush.task.task22.task2201;

public class TooShortStringFirstThreadException extends RuntimeException {
    public TooShortStringFirstThreadException() {
    }

    public TooShortStringFirstThreadException(String message) {
        super(message);
    }

    public TooShortStringFirstThreadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooShortStringFirstThreadException(Throwable cause) {
        super(cause);
    }

    public TooShortStringFirstThreadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
