package com.javarush.task.task20.task2020;

import java.io.*;
import java.util.logging.Logger;

/* 
Сериализуйте Person
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString = "Hello, ";
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Name", "LastName", "Russia", Sex.MALE);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(person);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        Person loadPerson = (Person) in.readObject();
        //loadPerson = new Person(loadPerson.firstName, loadPerson.lastName, loadPerson.country, loadPerson.sex);
        System.out.println(loadPerson.firstName);
        System.out.println(loadPerson.lastName);
        System.out.println(loadPerson.fullName);
        System.out.println(loadPerson.greetingString);
        System.out.println(loadPerson.country);
        System.out.println(loadPerson.sex);
        System.out.println(loadPerson.outputStream);
        System.out.println(loadPerson.logger);
    }
}
