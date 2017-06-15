package com.javarush.task.task21.task2102;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/* 
Сравниваем модификаторы
*/
public class Solution {
    public static void main(String[] args) {
/*
        System.out.println(getMainMethod().getName());
        System.out.println(getMainMethod().getModifiers());
        System.out.println(Modifier.toString(getMainMethod().getModifiers()));
        System.out.println(Modifier.PUBLIC);
        System.out.println(Modifier.toString(Modifier.PUBLIC));
        System.out.println(Modifier.PRIVATE);
        System.out.println(Modifier.toString(Modifier.PRIVATE));
        System.out.println(Modifier.STATIC);
        System.out.println(Modifier.toString(Modifier.STATIC));
*/

        int modifiersOfThisClass = Solution.class.getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.PUBLIC));   //true
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.STATIC));   //false

        int modifiersOfMethod = getMainMethod().getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfMethod, Modifier.STATIC));      //true
    }

    public static boolean isAllModifiersContainSpecificModifier(int allModifiers, int specificModifier) {
        return Modifier.toString(allModifiers).contains(Modifier.toString(specificModifier));

    }

    private static Method getMainMethod() {
        Method[] methods = Solution.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;
        }

        return null;
    }
}
