package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1) {
                    count++;
                    a[i][j] = 0;
                    for (int k = i + 1; k < a.length; k++) {
                        if (a[k][j] == 1) {
                            a[k][j] = 0;
                        } else break;
                    }
                    for (j = j + 1; j < a.length; j++) {
                        if (a[i][j] == 1) {
                            a[i][j] = 0;
                            for (int k = i + 1; k < a.length; k++) {
                                if (a[k][j] == 1) {
                                    a[k][j] = 0;
                                } else break;
                            }
                        } else break;
                    }
                }
            }
        }
        return count;
    }
}
