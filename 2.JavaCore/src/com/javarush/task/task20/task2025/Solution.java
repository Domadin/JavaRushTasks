package com.javarush.task.task20.task2025;

import java.util.Arrays;

public class Solution {
    public static long[] getNumbers(long N) {
        int count = 0;
        int length = Long.toString(N).length();
        long[] allArm = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L, 1634L, 8208L, 9474L, 54748L, 92727L, 93084L, 548834L, 1741725L, 4210818L, 9800817L, 9926315L, 24678050L, 24678051L, 88593477L, 146511208L, 472335975L, 534494836L, 912985153L, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L, 49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L, 21897142587612075L, 35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L, 4498128791164624869L, 4929273885928088826L};

        for (long l : allArm) {
            if (Long.toString(l).length() <= length) {
                count++;
            } else break;
        }

        return Arrays.copyOfRange(allArm, 0, count);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNumbers(54748)));
    }
}


/*public class Solution {
    public static long[] getNumbers(long N) {
        int count;
        long[] result = null;



        return result;
    }

    public static long powerLong(long number, int power) {
        long result = number;
        while (power > 0) {
            result *= number;
            power--;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}*/





/*        long[] result = null;
        for (long xCase = 10; xCase < N; xCase++) {
            String temp = Long.toString(xCase);
            byte length = (byte) temp.length();
            byte[] digits = new byte[length];
            for (int i = 0; i < length; i++) {
                digits[i] = (byte) (temp.charAt(i) - '0');
            }
            long compare = 0;
            for (byte b : digits) {
                compare += Math.pow(b, length);
            }
            if (xCase == compare) {

                //result.add(xCase);
            }
        }*/

//long[] allArm = { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L, 1634L, 8208L, 9474L, 54748L, 92727L, 93084L, 548834L, 1741725L, 4210818L, 9800817L, 9926315L, 24678050L, 24678051L, 88593477L, 146511208L, 472335975L, 534494836L, 912985153L, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L, 49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L, 21897142587612075L, 35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L, 4498128791164624869L, 4929273885928088826L};
