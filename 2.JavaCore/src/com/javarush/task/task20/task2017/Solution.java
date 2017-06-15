package com.javarush.task.task20.task2017;

import java.io.*;

/* 
Десериализация
*/
public class Solution implements Serializable {
    public static Solution test;

    public Object getOriginalObject(ObjectInputStream objectStream) {
        try {
            Object original = objectStream.readObject();
            System.out.println(original.getClass());
            A AOriginal = (A) original;
            System.out.println(AOriginal.getClass());
            return original;
        } catch (Exception e) {
            System.out.println("Исключение");
            return null;
        }
    }

    public class A implements Serializable {
        public A() {
            System.out.println("inside A");
        }
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) throws IOException {
        test = new Solution();
        test.call();
    }

    public void call() throws IOException {
        B ATest = new B();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(ATest);
        ByteArrayInputStream bint = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream inp = new ObjectInputStream(bint);
        B load = (B) test.getOriginalObject(inp);
    }
}
