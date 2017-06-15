package com.javarush.task.task35.task3501;

public class GenericStatic {
    public static <E> E someStaticMethod(E genericObject) {
        System.out.println(genericObject);
        return genericObject;
    }
}
