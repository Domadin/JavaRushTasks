package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] result = new Solution[2];
        for (int i = 0; i < 2; i++) {
            Solution solution = new Solution();
            for (int j = 0; j < 2; j++) {
                solution.innerClasses[j] = solution.new InnerClass();
            }
            result[i] = solution;
        }

/*        Solution solution = new Solution();
        solution.innerClasses[0] = solution.new InnerClass();
        solution.innerClasses[1] = solution.new InnerClass();

        Solution solution1 = new Solution();
        solution1.innerClasses[0] = solution1.new InnerClass();
        solution1.innerClasses[1] = solution1.new InnerClass();*/

        return result;
    }

    public static void main(String[] args) {

    }
}
