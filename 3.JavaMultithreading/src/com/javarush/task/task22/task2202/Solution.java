package com.javarush.task.task22.task2202;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush 1:- лучший сервис обучения Java."));
        System.out.println(getPartOfString("Первое 2:начало третье четвертое конец НЕТ НЕТ НЕТ"));
        System.out.println(getPartOfString("Первое 3:начало третье четвертое конец"));
        System.out.println(getPartOfString("Первое 4:начало третье ИСКЛЮЧЕНИЕ"));
        System.out.println(getPartOfString("5:ИСКЛЮЧЕНИЕ"));
    }

    public static String getPartOfString(String string) {
/*        try {
            String[] array = string.split(" ");
            StringBuilder result = new StringBuilder();
            for (int i = 1; i < 5; i++) {
                result.append(array[i]).append(" ");
            }
            return result.toString();
        } catch (RuntimeException e) {
            throw new TooShortStringException();
        }*/

        int found = 0;
        int index = -1;
        try {
            do {
                index = string.indexOf(" ", index + 1);
                found++;
            } while (found < 5);
            if (index < 0 && found == 5)
                return string.substring(string.indexOf(" ") + 1, string.length());
            else
                return string.substring(string.indexOf(" ") + 1, index);
        } catch (RuntimeException e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
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
}
