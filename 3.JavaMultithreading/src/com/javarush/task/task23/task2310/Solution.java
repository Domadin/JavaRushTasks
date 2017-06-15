package com.javarush.task.task23.task2310;

/* 
Напряги извилины!
*/
public class Solution {
    private static int test = 10;
    private String name;

    Solution(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    private void sout() {
        new Solution("sout") {
            void printName() {
                System.out.println(getName());
                test();
                print();
            }

            void print() {
                System.out.println(test);
            }
        }.printName();
    }

    private void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        new Solution("main").sout();
    }
}
