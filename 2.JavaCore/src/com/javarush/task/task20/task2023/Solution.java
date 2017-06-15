package com.javarush.task.task20.task2023;

public class Solution {
    public static void main(String[] s) {
        A a = new B();
        a.method2();
    }

    public static class A {
        public void method1() {
            System.out.println("A class, method1");
        }   //3

        public void method2() {
            System.out.println("A class, method2");
            method1();
        }   //2
    }

    public static class B extends A {
        @Override
        public void method1() {
            super.method1();
            System.out.println("B class, method1");
        }   //4

        @Override
        public void method2() {
            System.out.println("B class, method2");
            super.method2();
        }
    }

    public static class C extends B {
        @Override
        public void method1() {
            System.out.println("C class, method1");
        }

        @Override
        public void method2() {
            System.out.println("C class, method2");
            super.method1();
        }   //1

        public void method3() {
            System.out.println("C class, method3");
        }
    }
}

/*
public class Solution {
    public static void main(String[] s) {
        A a = new C();
        a.method2();
    }

    public static class A {
        private void method1() {
            System.out.println("A class, method1");
        }   //3

        public void method2() {
            System.out.println("A class, method2");
            method1();
        }   //2
    }

    public static class B extends A {
        private void method1() {
            super.method2();
            System.out.println("B class, method1");
        }   //4

        @Override
        public void method2() {
            System.out.println("B class, method2");
        }
    }

    public static class C extends B {
        private void method1() {
            System.out.println("C class, method1");
        }

        @Override
        public void method2() {
            System.out.println("C class, method2");
            super.method1();
        }   //1
    }
}
*/
