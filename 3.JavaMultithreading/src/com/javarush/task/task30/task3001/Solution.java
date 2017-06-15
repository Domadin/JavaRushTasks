package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) throws NumberFormatException {
        //System.out.println(NumerationSystemType._2.getNumerationSystemIntValue());

        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) throws NumberFormatException {

        BigInteger integer = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());
        String result = integer.toString(expectedNumerationSystem.getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem, result);
    }
}
