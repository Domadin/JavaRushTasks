package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student result = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                result = student;
                break;
            }
        }
        return result;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student result = null;
        double maxGrade = 0;
        for (Student student : students) {
            if (student.getAverageGrade() > maxGrade) {
                result = student;
                maxGrade = student.getAverageGrade();
            }
        }
        return result;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public Student getStudentWithMinAverageGrade() {
        Student result = null;
        double minGrade = Double.MAX_VALUE;
        for (Student student : students) {
            if (student.getAverageGrade() < minGrade) {
                result = student;
                minGrade = student.getAverageGrade();
            }
        }
        return result;
    }
}