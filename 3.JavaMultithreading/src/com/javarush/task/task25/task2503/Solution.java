package com.javarush.task.task25.task2503;

import java.util.List;

/*
Свой enum
*/
public class Solution {
    /**
     * Output:
     * <p/>
     * Account Number
     * Available Amount
     * Bank Name
     * --------------------
     * Account Number
     * Bank Name
     */
    public static void main(String[] args) {

        Column.configureColumns(Column.AccountNumber, Column.Amount, Column.BankName);

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }

        System.out.println("--------------------");
        Column.Amount.hide();

        for (Columnable columnable : Column.getVisibleColumns()) {
            System.out.println(columnable.getColumnName());
        }
    }
}
