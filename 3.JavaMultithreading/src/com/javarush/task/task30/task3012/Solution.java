package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        int[] baseNums = {1, 3, 9, 27, 81, 243, 729, 2187};
        String s = convertToBalancedTernary(number);
        StringBuilder result = new StringBuilder(number + " =");
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != '0')
                result.append(" ").append(charArray[i]).append(" ").append(baseNums[i]);
        }
        System.out.println(result);
    }

    public String convertToBalancedTernary(int number) {
        StringBuilder result = new StringBuilder();
        int integer = number / 3;
        int remainder = number % 3;
        switch (remainder) {
            case 0:
                result.append("0");
                break;
            case 1:
                result.append("+");
                break;
            case 2:
                result.append("-");
                integer++;
                break;
        }
        switch (integer) {
            case 0:
                break;
            case 1:
                result.append("+");
                break;
            default:
                result.append(convertToBalancedTernary(integer));
        }
        return result.toString();
    }
}