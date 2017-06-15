package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class StudentsDataBase {
    public static List<Student> students = new ArrayList<>();

    public static void addInfoAboutStudent(Student student) {
        students.add(student);
        printInfoAboutStudent(student);

    }

    public static void printInfoAboutStudent(Student student) {
        System.out.println("Имя: " + student.getName() + " Возраст: " + student.getAge());
    }

    public static void removeStudent(int index) {
        if (index > -1 && index < students.size())
            students.remove(index);
    }

    public static void findDimaOrSasha() {
        for (int i = 0; i < students.size(); i++) {
            String name = students.get(i).getName();
            if (name.equals("Dima")) {
                System.out.println("Студент Dima есть в базе.");
                break;
            }
            if (name.equals("Sasha")) {
                System.out.println("Студент Sasha есть в базе.");
                break;
            }
        }
    }
    /*    public static void findDimaOrSasha() {
        for (int i = 0; i < students.size(); i++) {
            String name = students.get(i).getName();
            if (name.equals("Dima") || name.equals("Sasha")) {
                System.out.printf("Студент %s есть в базе.\n", name);
                break;
            }
        }
    }*/
}