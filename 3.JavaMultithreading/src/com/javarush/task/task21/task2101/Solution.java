package com.javarush.task.task21.task2101;

public class Solution {
    public static void main(String[] args) {
/*        System.out.println("7 " + Integer.toBinaryString(33554431));
        System.out.println("6 " + Integer.toBinaryString(67108863));
        System.out.println("5 " + Integer.toBinaryString(134217726));
        System.out.println("4 " + Integer.toBinaryString(268435452));
        System.out.println("3 " + Integer.toBinaryString(536870904));
        System.out.println("2 " + Integer.toBinaryString(1073741808));
        System.out.println("1 " + Integer.toBinaryString(2147483632));
        System.out.println("0 " + Integer.toBinaryString(-64));
        System.out.println("М " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("0 " + Integer.toBinaryString(-1));


        System.out.println("Разделение");

        System.out.println("7 " + String.format("%32s", Integer.toBinaryString(33554431)).replace(' ', '0'));
        System.out.println("6 " + String.format("%32s", Integer.toBinaryString(67108863)).replace(' ', '0'));
        System.out.println("5 " + String.format("%32s", Integer.toBinaryString(134217726)).replace(' ', '0'));
        System.out.println("4 " + String.format("%32s", Integer.toBinaryString(268435452)).replace(' ', '0'));
        System.out.println("3 " + String.format("%32s", Integer.toBinaryString(536870904)).replace(' ', '0'));
        System.out.println("2 " + String.format("%32s", Integer.toBinaryString(1073741808)).replace(' ', '0'));
        System.out.println("1 " + String.format("%32s", Integer.toBinaryString(2147483632)).replace(' ', '0'));
        System.out.println("0 " + String.format("%32s", Integer.toBinaryString(-64)).replace(' ', '0'));
        System.out.println("М " + String.format("%32s", Integer.toBinaryString(Integer.MAX_VALUE)).replace(' ', '0'));
        System.out.println("0 " + String.format("%32s", Integer.toBinaryString(-1)).replace(' ', '0'));


        System.out.println("Разделение");*/
        byte[] ip = new byte[]{(byte) 192, (byte) 328, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (ip[i] & mask[i]);
        }
        return bytes;
    }

    public static void print(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(getByteBinary(b) + " ");
        }
        System.out.println();
    }

    public static String getByteBinary(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 255)).replace(' ', '0');
    }
}
