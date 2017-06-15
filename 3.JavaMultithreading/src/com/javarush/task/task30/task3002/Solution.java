package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22 шестнадцатиричная
        System.out.println(convertToDecimalSystem("012"));  //10 восьмиричная
        System.out.println(convertToDecimalSystem("0b10")); //2  двоичная
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        Integer.parseInt("10",10);

        if (s.startsWith("0x")) {
            s = s.substring(s.indexOf("x") + 1);
            return String.valueOf(Integer.parseInt(s, 16));
        } else if (s.startsWith("0b")) {
            s = s.substring(s.indexOf("b") + 1);
            return String.valueOf(Integer.parseInt(s, 2));
        } else if (s.startsWith("0")) {
            return String.valueOf(Integer.parseInt(s, 8));
        }
        return String.valueOf(Integer.parseInt(s));
    }
}
