package com.javarush.task.task22.task2201;

public class TooShortStringSecondThreadException extends RuntimeException {
    public TooShortStringSecondThreadException() {
    }

    public TooShortStringSecondThreadException(String message) {
        super(message);
    }

    public TooShortStringSecondThreadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooShortStringSecondThreadException(Throwable cause) {
        super(cause);
    }

    public TooShortStringSecondThreadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
