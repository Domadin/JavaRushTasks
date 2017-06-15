package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String[] input = reader.readLine().split(" ");
            int year = Integer.parseInt(input[input.length - 1]) - 1900;
            int month = Integer.parseInt(input[input.length - 2]) - 1;
            int day = Integer.parseInt(input[input.length - 3]);
            StringBuilder buildName = new StringBuilder();
            for (int i = 0; i < input.length - 3; i++) {
                buildName.append(input[i]).append(" ");
            }
            String name = buildName.toString().trim();
            PEOPLE.add(new Person(name, new Date(year, month, day)));
        }
        reader.close();
        for (Person person : PEOPLE) {
            System.out.println(person.getName() + " " + person.getBirthday());
        }
    }
}
