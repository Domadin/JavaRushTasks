package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        int id = Integer.parseInt(args[0]);
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String data = reader.readLine();
            String[] dataSplit = data.split(" ", 2);
            int productID = Integer.parseInt(dataSplit[0]);
            if (productID == id) {
                String[] dataSplit1 = dataSplit[1].split(" ");
                StringBuilder productName = new StringBuilder();
                for (int i = 0; i < dataSplit1.length - 2; i++) {
                    productName.append(dataSplit1[i]).append(" ");
                }
                double price = Double.parseDouble(dataSplit1[dataSplit1.length - 2]);
                int quantity = Integer.parseInt(dataSplit1[dataSplit1.length - 1]);
                System.out.println(productID + " " + productName + " " + price + " " + quantity);
            }
        }
    }
}
