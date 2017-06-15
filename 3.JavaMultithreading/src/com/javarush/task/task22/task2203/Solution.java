package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            return string.substring(string.indexOf("\t") + 1, string.indexOf("\t", string.indexOf("\t") + 2));
        } catch (Exception e) {
            throw new TooShortStringException(e);
        }

/*        try {
            String[] array = string.split("\t");
            return array[1];
        } catch (Exception e) {
            throw new TooShortStringException();
        }*/
    }

    public static class TooShortStringException extends Exception {
        public TooShortStringException() {
        }

        public TooShortStringException(String message) {
            super(message);
        }

        public TooShortStringException(String message, Throwable cause) {
            super(message, cause);
        }

        public TooShortStringException(Throwable cause) {
            super(cause);
        }

        public TooShortStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java\t."));
    }
}
