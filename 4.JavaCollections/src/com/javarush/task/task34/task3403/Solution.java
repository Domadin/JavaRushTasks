package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion(2);
        solution.recursion(3);
        solution.recursion(7);
        solution.recursion(9);
        solution.recursion(15);
        solution.recursion(28);
        solution.recursion(105);
        solution.recursion(111);
        solution.recursion(132);
        solution.recursion(32111);
    }

    public void recursion(int n) {

        int divider = 2;
        while (n % divider != 0) {
            divider++;
        }

        System.out.print(divider);
        int result = n / divider;
        if (result != 1) {
            System.out.print(" ");
            recursion(result);
        } else System.out.println();
    }
}
