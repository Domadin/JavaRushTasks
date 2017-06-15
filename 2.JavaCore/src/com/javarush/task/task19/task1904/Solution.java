package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String personData = fileScanner.nextLine();
            String[] data = personData.split(" ");
            String firstName = data[1];
            String lastName = data[0];
            String middleName = data[2];
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String date = data[3] + data[4] + data[5];
            Date birthday = new Date();
            birthday = dateFormat.parse(date);
            return new Person(firstName, middleName, lastName, birthday);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
