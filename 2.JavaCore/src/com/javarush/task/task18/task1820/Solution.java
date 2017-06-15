package com.javarush.task.task18.task1820;

//C:\test\test1.txt

/**
 * Способ 1 - Scanner
 */

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String firstFile = fileNameReader.readLine();
        String secondName = fileNameReader.readLine();
        Scanner scanFile = new Scanner(new File(firstFile)).useLocale(Locale.US);
        BufferedWriter writer = new BufferedWriter(new FileWriter(secondName));
        while (scanFile.hasNextFloat()) {
            int digit = Math.round(scanFile.nextFloat());
            writer.write(digit + " ");
        }
        scanFile.close();
        writer.close();
    }
}

/**
 * Способ 2. FileReader/Writer
 */

/*
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String firstFile = fileNameReader.readLine();
        String secondName = fileNameReader.readLine();
        BufferedReader reader = new BufferedReader(new FileReader(firstFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(secondName));
        String file;
        while (reader.ready()) {
            file = reader.readLine();
            String[] data = file.split(" ");
            for (String s : data) {
                int i = Math.round(Float.parseFloat(s));
                writer.write(i + " ");
            }
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}*/
/**
 * Способ 3. Input/OutputStream
 */
/*
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = cin.readLine();
        String filename2 = cin.readLine();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename1));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename2));
        StringBuilder str = new StringBuilder();
        while (inputStream.available() > 0) {
            str.append(Character.toChars(inputStream.read()));
        }
        String[] numbers = str.toString().split(" ");
        for (String number : numbers) {
            int digit = Math.round(Float.parseFloat(number));
            outputStream.write(Integer.toString(digit).getBytes());
            outputStream.write(32);
        }
        inputStream.close();
        outputStream.close();
    }
}*/
