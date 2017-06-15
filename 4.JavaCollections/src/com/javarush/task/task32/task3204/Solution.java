package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        String charSet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm01234567890123456789";
        Random random = new Random();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int i = 0; i < 5; i++) {
            outputStream.write(charSet.charAt(random.nextInt(charSet.length())));
        }

        outputStream.write(charSet.charAt(random.nextInt(26)));
        outputStream.write(charSet.charAt(random.nextInt(26) + 26));
        outputStream.write(charSet.charAt(random.nextInt(20) + 52));

        return outputStream;
    }
}